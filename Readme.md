# ShareFileLikeFTPViaHTTP
  
  
# 1 - Expose 8080 port to wifi/hotspot network.

Below command for enable Fedora to expose 8080 port for connected wifi or hotspot.  
  
>sudo firewall-cmd --zone=public --add-port=8080/tcp --permanent  
  
>sudo firewall-cmd --reload  
  
  
# 2 - configure this app
  
  
  set folderPath in properties file.  
  
  in my case:  
>folderPath=/run/media/dipakugale/New Volume/$ AccessFromHere/SpringBootAws  folderPath=/run/media/dipakugale/New Volume/$ AccessFromHere/SpringBootAws
  
  
# 3 - Access from browser    
 
Get ip4
>ip a  
  
  http://192.168.50.144:8080/

![image](https://github.com/user-attachments/assets/3d65c306-ec7e-4672-91cf-4da797fdf7a5)

_______________________
Thank you!
