# StaffChat
A way for staff members to chat privately.

### Contributing
Contributions are appreciated.    
If you have any suggestions or bugs to report, please create an [issue](https://github.com/Troughy/StaffChat/issues).  

### Building
Download Apache Maven from [here](https://maven.apache.org/download.cgi). Instructions can be found [here](https://maven.apache.org/install.html).  
This project uses the Azul Zulu JDK 17.0.4. Download from [here](https://www.azul.com/downloads/?version=java-17-lts&package=jdk).  
Run `mvn clean package` in the project's directory. This will create the `StaffChat-X.X.jar` file in the `target` folder.

### Latest build
If you want the latest build (aka `nightly build`), click [here](https://github.com/Troughy/StaffChat/actions/workflows/maven.yml), click on the latest successful workflow run, scroll down to the `Artifacts`, and click on `StaffChat-latest`. This will download a zip file which will contain the plugin jar file.
  
  
1.19+ - Check out [No Chat Reports](https://www.curseforge.com/minecraft/mc-mods/no-chat-reports) for the client, and [NoEncryption](https://www.spigotmc.org/resources/noencryption.102902/) for the server. No Chat Reports also removes the warning you get when you escape the prefix (with \\).
