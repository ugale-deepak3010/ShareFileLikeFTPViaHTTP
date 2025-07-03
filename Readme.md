# Share File Explorer Like FTP Via HTTP using spring boot.
  
  
# 1 - Expose 8080 port to wifi/hotspot network.

Below command for enable Fedora to expose 8080 port for connected wifi or hotspot.  
  
>sudo firewall-cmd --zone=public --add-port=8080/tcp --permanent  
  
>sudo firewall-cmd --reload  
  
  
# 2 - configure this app
  
  
  set folderPath in properties file.  
  
  in my case:  
>folderPath=/run/media/dipakugale/New Volume/$ AccessFromHere/SpringBootAws  folderPath=/run/media/dipakugale/New Volume/$ AccessFromHere/SpringBootAws
  
  
# 3 -  Get Device IP
 
Get ip 4 from terminal
>ip a  

![image](https://github.com/user-attachments/assets/8723631d-2597-4a7d-b085-30128884c6ee)

# 4 - Access from browser
  
  http://192.168.50.144:8080/

![image](https://github.com/user-attachments/assets/3d65c306-ec7e-4672-91cf-4da797fdf7a5)

or from another devices
![image](https://github.com/user-attachments/assets/5aa4484f-1b97-4cb8-b7c6-3e2a63372745)

_______________________
Thank you!
