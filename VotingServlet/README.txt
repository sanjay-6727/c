VOTING ELIGIBILITY SERVLET — TOMCAT 9

Requirements:
- JDK 8, 11, or 17
- Apache Tomcat 9
- Tomcat 9 uses javax.servlet.* (NOT jakarta.servlet.*)

Folder structure:
VotingApp/
  index.html
  VotingServlet.java
  WEB-INF/
    web.xml
    classes/   (compiled class goes here)

1. Extract this ZIP.
2. Copy the VotingApp folder into your Tomcat 9 webapps folder.
3. Open PowerShell and change directory into VotingApp.
4. Compile (edit the Tomcat path if yours differs):

   $tomcat = "C:\path\to\apache-tomcat-9"
   javac -cp "$tomcat\lib\servlet-api.jar" -d ".\WEB-INF\classes" ".\VotingServlet.java"

5. Start Tomcat:
   & "$tomcat\bin\startup.bat"

6. Open:
   http://localhost:8080/VotingApp/

PS C:\Users\sanja\Downloads\apache-tomcat-10.1.60-windows-x64\apache-tomcat-10.1.60\webapps\VotingServlet> ls


    Directory: C:\Users\sanja\Downloads\apache-tomcat-10.1.60-windows-x64\apache-tomcat-10.1.60\webapps\VotingServlet


Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
d-----        15-10-2026     10:24                WEB-INF
-a----        15-10-2026     10:03            603 index.html
-a----        15-10-2026     10:03            994 README.txt
-a----        15-10-2026     10:27           1927 VotingServlet.java


PS C:\Users\sanja\Downloads\apache-tomcat-10.1.60-windows-x64\apache-tomcat-10.1.60\webapps\VotingServlet> cd web-inf
PS C:\Users\sanja\Downloads\apache-tomcat-10.1.60-windows-x64\apache-tomcat-10.1.60\webapps\VotingServlet\web-inf> ls


    Directory:
    C:\Users\sanja\Downloads\apache-tomcat-10.1.60-windows-x64\apache-tomcat-10.1.60\webapps\VotingServlet\web-inf


Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
d-----        15-10-2026     10:28                classes
-a----        15-10-2026     10:03            578 web.xml


PS C:\Users\sanja\Downloads\apache-tomcat-10.1.60-windows-x64\apache-tomcat-10.1.60\webapps\VotingServlet\web-inf>

Example:
Name = Arun
Age = 20
Output = Name: Arun — Eligible to vote

Note:
- If VotingServlet.java is in the VotingApp root, the compile command above is correct.
- If you have already copied VotingApp into webapps, compile from that folder.
- Ensure WEB-INF\classes exists before compiling.
