# FTX Rest Assured Testing
## Descriptions

### 5 Ways to execute testing
<ul>
    <li>TestNG.xml </li>
    <li>pom.xml </li>
    <ul>
        <li>2 plugins are required to excute projects throught maven</li>
        <ul>
        <li>maven-compiler-plugin</li>
        <li>maven-surefire-plugin</li>
        </ul>
    </ul>
    <li>Command Prompt </li>
    <ol>
        <li>Windows</li>
        <ol>
            <li>Download and install maven</li>
            <li>CMD: cd to maven pom directory</li>
            <li>CMD: mvn clean install</li>
        </ol>
        <li>Mac</li>
        <ol>
            <li>Download and install brew and maven in terminal</li>
            <li>Terminal: /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)" </li>
            <li>Terminal: echo "export PATH=/opt/homebrew/bin:$PATH" >> ~/.zshrc </li>
            <li>Terminal: brew intall maven </li>
            <li>Terminal: cd to project directory </li>
            <li>Terminal: mvn clean install </li>
        </ol>
    </ol>
    <li>Run.bat </li>
    <ol>
        <li>Windows</li>
        <ol>
            <li>Create bat file with scripts below: </li>
            <li>cd [Project Directory]<br/>
            mvn clean install</li>
            <li>Double click to execte the bat file</li>
        </ol>
        <li>Mac</li>
        <ol>
            <li>Create shellscript file with scripts below: </li>
            <li>cd [Project Directory]<br/>
            mvn clean install</li>
            <li>Terminal: bash xx.sh</li>
        </ol>
    </ol>
    <li>Jenkins </li>
    <ol>
        <li>Mac</li>
        <ol>
            <li>Install Jenkins</li>
            <li>Add Project and Environment Variable in Jenkins:</li>
            <ul>
                <li>MAVEN_HOME : /opt/homebrew/Cellar/maven/3.8.4/libexec</li>
                <li>PATH : $MAVEN_HOME/bin:$PATH</li>
            </ul>
            <li>Execute build for the Project </li>
        </ol>
    </ol>
</ul>