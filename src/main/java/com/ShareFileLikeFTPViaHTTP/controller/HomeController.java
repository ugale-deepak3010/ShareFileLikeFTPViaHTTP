package com.ShareFileLikeFTPViaHTTP.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class HomeController {
	
	@Value("${folderPath}")
    private String folderPath;

	

	@GetMapping(value = { "/", "/**" })
	public ResponseEntity<?> browse(HttpServletRequest request) throws IOException {
		
		Path ROOT = Paths.get(folderPath);
		
		
		String requestPath = URLDecoder.decode(request.getRequestURI(), "UTF-8");

		Path target = ROOT.resolve(requestPath.substring(1)).normalize();

		// Protect against path traversal
		if (!target.startsWith(ROOT)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied. Manually restricted at code level");
		}

		File file = target.toFile();

		if (file.isDirectory()) {
			StringBuilder html = new StringBuilder("<h2>Index of ").append(requestPath).append("</h2><ul>");
			if (!target.equals(ROOT)) {
				html.append("<li><a href=\"../\">../</a></li>");
			}

			for (File f : Objects.requireNonNull(file.listFiles())) {
				String name = f.getName();
				String link = URLEncoder.encode(name, "UTF-8").replace("+", "%20");
				if (f.isDirectory()) {
					link += "/";
					name += "/";
				}
				html.append("<li><a href=\"").append(link).append("\">").append(name).append("</a></li>");
			}

			html.append("</ul>");
			return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html.toString());
			
		} else if (file.isFile()) {
			Resource resource = new FileSystemResource(file);
			return ResponseEntity.ok()
					.contentType(
							MediaTypeFactory.getMediaType(file.getName()).orElse(MediaType.APPLICATION_OCTET_STREAM))
					.contentLength(file.length())
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
