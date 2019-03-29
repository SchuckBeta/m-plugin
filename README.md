# Maven 常用插件的使用
## properties：
<!-- <properties> -->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
 </properties>

## maven-compiler-plugin：
#### 编译Java源码，一般只需设置编译的jdk版本
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.6.0</version>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
    </configuration>
</plugin>

## maven-install-plugin 生成Jar到库：
#### 生成Jar到库
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-install-plugin</artifactId>
	<executions>
		<execution>
			<phase>install</phase>
			<goals>
				<goal>install-file</goal>
			</goals>
			<configuration>
				<packaging>jar</packaging>
				<artifactId>${project.artifactId}</artifactId>
				<groupId>${project.groupId}</groupId>
				<version>${project.version}</version>
				<file>
					${project.build.directory}/${curproject.jarFinalName}.jar
				</file>
			</configuration>
		</execution>
	</executions>
</plugin>

## maven-war-plugin：
####  生成War：
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-war-plugin</artifactId>
	<version>2.4</version>
	<configuration>
	    <packagingExcludes>
		WEB-INF/classes/org/apache/ibatis/**,
		WEB-INF/classes/org/mybatis/spring/**
	    </packagingExcludes>
	    <warSourceExcludes>
		static/bootstrap/2.3.1/docs/**,
		,/**/*.jsp,
		test/**
	    </warSourceExcludes>
	    <webappDirectory>${project.build.directory}/${project.artifactId}</webappDirectory>
	    <webXml>${project.basedir}/target/jspweb.xml</webXml>
	    <warName>${project.artifactId}</warName>
	</configuration>
</plugin>

## maven-deploy-plugin ：
#### 发布Jar
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-deploy-plugin</artifactId>
	<executions>
		<execution>
		<phase>deploy</phase>
		<goals>
			<goal>deploy-file</goal>
		</goals>
		<configuration>
			<packaging>jar</packaging>
			<generatePom>true</generatePom>
			<url>${project.distributionManagement.repository.url}</url>
			<artifactId>${project.artifactId}</artifactId>
			<groupId>${project.groupId}</groupId>
			<version>${project.version}</version>
			<file>${project.build.directory}/${project.artifactId}.jar</file>
		</configuration>
		</execution>
	</executions>
</plugin>

## maven-dependency-plugin:
#### 用于复制依赖的jar包到指定的文件夹里
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <version>2.10</version>
    <executions>
        <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals>
                <goal>copy-dependencies</goal>
            </goals>
            <configuration>
                <outputDirectory>${project.build.directory}/lib</outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>

## maven-jar-plugin：
#### 打成jar时，设定manifest的参数，比如指定运行的Main class，还有依赖的jar包，加入classpath中
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-jar-plugin</artifactId>
	<version>2.4</version>
	<configuration>
		<excludes>
			<exclude>*.properties</exclude>
		</excludes>
		<archive> 
			<!-- 生成的jar中，包含pom.xml和pom.properties这两个文件 --> 
			<addMavenDescriptor>true</addMavenDescriptor> 
			<manifestFile>${project.build.outputDirectory}/META-INF/MANIFEST.MF</manifestFile>
			<!-- 生成MANIFEST.MF的设置 --> 
			<manifest> 
				<!--这个属性特别关键，如果没有这个属性，有时候我们引用的包maven库
				下面可能会有多个包，并且只有一个是正确的，其余的可能是带时间戳的，
				此时会在classpath下面把那个带时间戳的给添加上去，然后我们
				在依赖打包的时候，打的是正确的，所以两头会对不上，报错。--> 
				<useUniqueVersions>false</useUniqueVersions> 
				<!-- 为依赖包添加路径, 这些路径会写在MANIFEST文件的Class-Path下 --> 
				<addClasspath>true</addClasspath> 
				<!-- 这个jar所依赖的jar包添加classPath的时候的前缀，如果这个
				jar本身和依赖包在同一级目录，则不需要添加--> 
				<classpathPrefix>lib/</classpathPrefix> 
				<!-- jar启动入口类--> 
				<mainClass>com.test.Test</mainClass> 
			</manifest> <manifestEntries> 
			<!-- 在Class-Path下添加配置文件的路径 --> 
			<Class-Path>../config/</Class-Path> 
			<!-- 假如这个项目可能要引入一些外部资源，但是你打包的时候并不想把
			这些资源文件打进包里面，这个时候你必须在这边额外指定一些这些资源
			文件的路径，这个位置指定的话，要根据你预期的这些位置去设置，我这边
			所有jar都在lib下，资源文件都在config下，lib和config是同级的 --> 
			<!-- 同时还需要注意另外一个问题，假如你的pom文件里面配置了
			<scope>system</scope>,就是你依赖是你本地的资源，这个时候使用
			这个插件，classPath里面是不会添加，所以你得手动把这个依赖添加进
			这个地方，用空格隔开就行--> 
			</manifestEntries> 
		</archive> 
		<!-- jar包的位置--> 
		<outputDirectory>${project.build.directory}/lib</outputDirectory> 
		<includes> 
			<!-- 打jar包时，只打包class文件 --> 
			<!-- 有时候可能需要一些其他文件，这边可以配置，包括剔除的文件等等--> 
			<include>**/*.class</include> 
		</includes>
	</configuration> 
</plugin>

## maven-antrun-plugin:
#### 在maven中运行Ant任务，比如在打包阶段，对文件进行复制
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-antrun-plugin</artifactId>
    <version>1.7</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>run</goal>
            </goals>
            <configuration>
                <target name="copy">
                    <delete>
                        <fileset dir="target" includes="*.properties"></fileset>
                    </delete>
                    <copy todir="target">
                        <fileset dir="files"></fileset>
                    </copy>
                </target>
            </configuration>
        </execution>
    </executions>
</plugin>

## wagon-maven-plugin:
#### 用于一键部署，把本地打包的jar文件，上传到远程服务器上，并执行服务器上的shell命令
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>wagon-maven-plugin</artifactId>
    <version>1.0</version>
    <configuration>
        <serverId>crawler</serverId>
        <fromDir>target</fromDir>
        <includes>*.jar,*.properties,*.sh</includes>
        <url>sftp://59.110.162.178/home/zhangxianhe</url>
        <commands>
            <command>chmod 755 /home/zhangxianhe/update.sh</command>
            <command>/home/zhangxianhe/update.sh</command>
        </commands>
        <displayCommandOutputs>true</displayCommandOutputs>
    </configuration>
</plugin>

## tomcat7-maven-plugin:
#### 用于远程部署Java Web项目
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <url>http://59.110.162.178:8080/manager/text</url>
        <username>linjinbin</username>
        <password>linjinbin</password>
    </configuration>
</plugin>

## cobertura-maven-plugin:
#### 测试覆盖率插件
<plugin>
	<groupId>org.codehaus.mojo</groupId>
	<artifactId>cobertura-maven-plugin</artifactId>
	<version>2.6</version>
	<configuration>
	<formats>
	<format>html</format>
	<format>xml</format>
	</formats>
	</configuration>
</plugin>

## maven-shade-plugin:
####用于把多个jar包，打成1个jar包
####一般Java项目都会依赖其他第三方jar包，最终打包时，希望把其他jar包包含在一个jar包里
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>2.4.3</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <transformers>
                    <transformer
                        implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <manifestEntries>
                            <Main-Class>com.meiyou.topword.App</Main-Class>
                            <X-Compile-Source-JDK>${maven.compile.source}</X-Compile-Source-JDK>
                            <X-Compile-Target-JDK>${maven.compile.target}</X-Compile-Target-JDK>
                        </manifestEntries>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>

## maven-resources-plugin：
<plugin>
	<artifactId>maven-resources-plugin</artifactId>
	<version>2.6</version>
	<executions>
		<execution>
			<id>copy-xmls</id>
			<phase>process-sources</phase>
			<goals>
				<goal>copy-resources</goal>
			</goals>
			<configuration>
				<encoding>UTF-8</encoding>
				<outputDirectory>${basedir}/target/classes</outputDirectory>
				<resources>
					<resource>
						<directory>${basedir}/src/main/java</directory>
						<includes>
							<include>**/*.xml</include>
							<include>**/*.properties</include>
							<include>**/*.xml</include>
						</includes>
						<excludes>
							<exclude>**/*.json</exclude>
						</excludes>
						<filtering>false</filtering>
					</resource>
					<resource>
						<directory>${project.basedir}/src/main/resources</directory>
						<excludes>
							<exclude>**/*.properties</exclude>
							<exclude>**/*.xml</exclude>
						</excludes>
						<filtering>true</filtering>
					</resource>
				</resources>
			</configuration>
		</execution>
	</executions>
</plugin>

## build-helper-maven-plugin：
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>build-helper-maven-plugin</artifactId>
    <version>1.8</version>
    <executions>
	<execution>
	    <id>add-resource</id>
	    <phase>generate-resources</phase>
	    <goals>
		<goal>add-resource</goal>
	    </goals>
	    <configuration>
		<resources>
		    <resource>
			<directory>src/main/java</directory>
			<includes>
			    <include>**/*.xml</include>
			</includes>
		    </resource>
		</resources>
	    </configuration>
	</execution>
    </executions>
</plugin> 


## maven-surefire-plugin：
<plugin>
	<artifactId>maven-surefire-plugin</artifactId>
	<version>2.9</version>
	<configuration>
		<suiteXmlFiles>
			<suiteXmlFile>testng.xml</suiteXmlFile>
		</suiteXmlFiles>
	</configuration>
	<executions>
		<execution>
			<id>default-test</id>
			<phase>test</phase>
			<goals>
				<goal>test</goal>
			</goals>
		</execution>
	</executions>
</plugin>


## maven-assembly-plugin： 
#### maven自定义(修改)编译后输出的war或jar文件名
#### 支持定制化打包方式，负责将整个项目按照自定义的目录结构打成最终的压缩包，方便实际部署、可在此处设置打包拷贝路径，配置，以及打包好的jar文件等
<plugin>  
    <groupId>org.apache.maven.plugins</groupId>  
    <artifactId>maven-assembly-plugin</artifactId>  
    <version>2.4</version>  
    <configuration>  
	<finalName>自定义文件包名</finalName>  
	<appendAssemblyId>false</appendAssemblyId>  
	<descriptors>  
	    <descriptor>${basedir}/src/main/assembly.xml</descriptor>  
	</descriptors>  
	<archive>
	    <manifest>
	        <mainClass>test.core.Core</mainClass>
	    </manifest>
	</archive>
	<descriptorRefs>
	    <descriptorRef>jar-with-dependencies</descriptorRef>
	</descriptorRefs>
    </configuration>  
    <executions>  
	<execution>  
	    <id>make-assembly</id>  
	    <phase>package</phase>  
	    <goals>  
		<goal>single</goal>  
	    </goals>  
	</execution>  
    </executions>  
</plugin>  

#### assembly.xml:
<?xml version="1.0" encoding="UTF-8"?>
<assembly xmlns="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.3" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.3 http://maven.apache.org/xsd/assembly-1.1.3.xsd">
    <id>dist</id>
    <formats>
        <format>zip</format>
    </formats>
    <includeBaseDirectory>true</includeBaseDirectory>

    <fileSets>
        <fileSet>
            <directory>src/main/bin</directory>
            <outputDirectory>bin/</outputDirectory>
        </fileSet>
        <fileSet>
            <directory>src/main/resources</directory>
            <outputDirectory>/</outputDirectory>
        </fileSet>

        <fileSet>
            <directory>${project.build.directory}</directory>
            <outputDirectory>/</outputDirectory>
            <includes>
                <include>*.jar</include>
            </includes>
        </fileSet>
    </fileSets>
    <dependencySets>
        <dependencySet>
            <outputDirectory>lib</outputDirectory>
            <scope>runtime</scope>
            <excludes>
                <exclude>${groupId}:${artifactId}</exclude>
            </excludes>
        </dependencySet>
    </dependencySets>
</assembly>

#### start.sh:
#!/bin/bash

ARTIFACT_ID=jd-bt-microservice-gateway-zuul
VERSION=0.0.1-SNAPSHOT
main_jar=${ARTIFACT_ID}-${VERSION}.jar

PIDS=`ps aux|grep ${main_jar} |grep -v "grep" |awk '{print $2}'`
if [ ! -z "$PIDS" ]; then
    echo "${ARTIFACT_ID} already started!"
    echo "PID: $PIDS"
    exit 0;
fi

BIN_PATH="${JAVA_HOME}/bin"
LOG_PATH="/Users/lihongxu/log/${ARTIFACT_ID}/"

mkdir -p $LOG_PATH

echo "ready start ${main_jar}";
nohup $BIN_PATH/java -server -Xms4096m -Xmx4096m -jar ../${main_jar} >$LOG_PATH/nohup.log 2>&1 &

sleep 5

PIDS=`ps aux|grep ${main_jar} |grep -v "grep" |awk '{print $2}'`

if [ ! -z "$PIDS" ]; then
    echo " ${ARTIFACT_ID} Started Successed,pid:${PIDS}"
    exit 0;
else
    echo " ${ARTIFACT_ID} Started Failed"
    exit 1;
fi


## maven-dependency-plugin:
####用来拷贝项目所有依赖的插件
<plugin> 
	<groupId>org.apache.maven.plugins</groupId> 
	<artifactId>maven-dependency-plugin</artifactId> 
	<executions> 
		<execution> 
			<id>copy-dependencies</id> 
			<phase>package</phase> 
			<goals> 
				<goal>copy-dependencies</goal> 
			</goals> 
			<configuration> 
				<!-- 拷贝项目依赖包到lib/目录下 --> 
				<outputDirectory>${project.build.directory}/lib</outputDirectory> 
				<!-- 间接依赖也拷贝 --> 
				<excludeTransitive>false</excludeTransitive> 
				<!-- 带上版本号 --> 
				<stripVersion>false</stripVersion> 
			</configuration> 
		</execution> 
	</executions> 
</plugin>

#### stop.sh:
#!/bin/bash
ARTIFACT_ID=jd-bt-microservice-gateway-zuul
VERSION=0.0.1-SNAPSHOT
main_jar=${ARTIFACT_ID}-${VERSION}.jar

PIDS=`ps aux|grep ${main_jar} |grep -v "grep" |awk '{print $2}'`

if [ ! -z "$PIDS" ]; then
    kill -9 "$PIDS"
    echo " ${ARTIFACT_ID} is stop !"
    echo " PID: $PIDS"
    exit 0;
fi