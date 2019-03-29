# Maven 常用插件的使用


## 常用变量：
~~~

${basedir} 项目根目录  
${project.basedir} 当前项目基础路径
${project.packaging} 打包类型，缺省为jar  
${project.xxx} 当前pom文件的任意节点的内容  
${project.parent.basedir}  上一级项目基础路径
${project.build.directory} 构建目录，缺省为target  
${project.build.outputDirectory} 构建过程输出目录，缺省为target/classes  
${project.build.finalName} 产出物名称，缺省为${project.artifactId}-${project.version}  
${project.build.finalName}：项目打包文件名
${maven.build.timestamp}： 获取打包时间
${path.separator}： 系统文件分隔符， linux下是冒号-->":", windows下是分号-->";"，
设置bootclasspath时分割不同的jar包

java.version Java 运行时环境版本 
java.vendor Java 运行时环境供应商 
java.vendor.url Java 供应商的 URL 
java.home Java 安装目录 
java.vm.specification.version Java 虚拟机规范版本 
java.vm.specification.vendor Java 虚拟机规范供应商 
java.vm.specification.name Java 虚拟机规范名称 
java.vm.version Java 虚拟机实现版本 
java.vm.vendor Java 虚拟机实现供应商 
java.vm.name Java 虚拟机实现名称 
java.specification.version Java 运行时环境规范版本 
java.specification.vendor Java 运行时环境规范供应商 
java.specification.name Java 运行时环境规范名称 
java.class.version Java 类格式版本号 
java.class.path Java 类路径 
java.library.path 加载库时搜索的路径列表 
java.io.tmpdir 默认的临时文件路径 
java.compiler 要使用的 JIT 编译器的名称 
java.ext.dirs 一个或多个扩展目录的路径 
os.name 操作系统的名称 
os.arch 操作系统的架构 
os.version 操作系统的版本 
file.separator 文件分隔符（在 UNIX 系统中是“/”） 
path.separator 路径分隔符（在 UNIX 系统中是“:”） 
line.separator 行分隔符（在 UNIX 系统中是“/n”） 
user.name 用户的账户名称 
user.home 用户的主目录 
user.dir 用户的当前工作目录 
~~~

## settings.xml：
~~~
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

	<pluginGroups>
	</pluginGroups>

	<proxies>
	</proxies>
  	<localRepository>C:/Users/Administrator/.m2/repository</localRepository> 
	<servers>
		<server>
			<id>tomcat_deploy</id>
			<username>tomcat</username>
			<password>tomcat</password>
		</server>
		<server>
			<id>nexus-releases</id>
			<username>admin</username>
			<password>admin123</password>
		</server>
		<server>
			<id>nexus-snapshots</id>
			<username>admin</username>
			<password>admin123</password>
		</server>
		<server>
			<id>tomcat-dev</id>
			<username>admin</username>
			<password>admin</password>
		</server>
		 <server>  
			<id>releases</id>  
			<username>admin</username>  
			<password>admin123</password>  
		  </server>  
		 <server>  
			  <id>snapshots</id>  
			  <username>admin</username>  
			  <password>admin123</password>  
		  </server>   
	</servers>

	<mirrors>
	</mirrors>

	<profiles>
		<profile>	
			<id>build-env</id>
			<properties>
				<maven.dbtask.skip>true</maven.dbtask.skip>
			</properties>
		</profile>

		<profile>
			<id>local-repositories</id>
			<repositories>
				<repository>
					<id>local-nexus</id>
					<url>http://localhost/nexus/content/groups/public/</url>
					<releases>
                       <enabled>true</enabled>
                       <updatePolicy>always</updatePolicy>
                   </releases>
                   <snapshots>
                       <enabled>true</enabled>
                       <updatePolicy>always</updatePolicy>
                   </snapshots>
				</repository>
			</repositories>
			<pluginRepositories>
				<pluginRepository>
					<id>local-nexus</id>
					<url>http://localhost/nexus/content/groups/public/</url>
					<releases>
                       <enabled>true</enabled>
                       <updatePolicy>always</updatePolicy>
                   </releases>
                   <snapshots>
                       <enabled>true</enabled>
                       <updatePolicy>always</updatePolicy>
                   </snapshots>
				</pluginRepository>
			</pluginRepositories>
		</profile>
		<profile>
			<id>default-build-param</id>
			<properties>
				<project.build.sourceEncoding>utf8</project.build.sourceEncoding>
				<maven.compiler.source>6</maven.compiler.source>
				<maven.compiler.target>6</maven.compiler.target>
				<maven.compiler.encoding>utf8</maven.compiler.encoding>
				<scm.username>user</scm.username>
				<scm.password>user</scm.password>

				<!-- <sonar.exclusions>
					**/src/main/java/org/**/*
					**/src/main/java/Net/PC15/**/*
					**/src/main/java/com/xxx/xxx/xxx/**/*
					**/src/main/java/com/xxx/xxx/test/**/*
					**/src/main/java/com/xxx/xxx/modules/act/**/*
					**/src/main/java/com/xxx/xxx/modules/sys/**/*
				</sonar.exclusions>-->
			</properties>
		</profile>
		<profile>
			<id>sonar</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<properties>
				<!--<sonar.jdbc.url>jdbc:mysql://localhost:3306/sonar?useUnicode=true&characterEncoding=utf8</sonar.jdbc.url>
				<sonar.jdbc.driver>com.mysql.jdbc.Driver</sonar.jdbc.driver>
				<sonar.jdbc.username>root</sonar.jdbc.username>
				<sonar.jdbc.password>123456</sonar.jdbc.password>
				<sonar.host.url>http://localhost:9000</sonar.host.url>-->
				<sonar.host.url>http://localhost:9000</sonar.host.url>
			</properties>
		</profile>
	</profiles>
	<activeProfiles>
		<activeProfile>build-env</activeProfile>
		<activeProfile>local-repositories</activeProfile>
		<activeProfile>default-build-param</activeProfile>
	</activeProfiles>
</settings>
~~~

## properties：
~~~
<properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
 </properties>
~~~

## maven-clean-plugin：
#### clean插件
~~~
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-clean-plugin</artifactId>
				<version>2.6.1</version>
			</plugin>
~~~

## maven-compiler-plugin：
#### 编译Java源码，一般只需设置编译的jdk版本
~~~
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.6.0</version>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <showWarnings>true</showWarnings>
    </configuration>
</plugin>
~~~

## maven-install-plugin 生成Jar到库：
#### 生成Jar到库
~~~
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
~~~

## maven-war-plugin：
####   war 打包插件, 设定war包名称不带版本号：
~~~
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
~~~

## maven-deploy-plugin ：
#### 发布Jar
~~~
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
~~~

## maven-jar-plugin：
#### 打成jar时，设定manifest的参数，比如指定运行的Main class，还有依赖的jar包，加入classpath中
~~~
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-jar-plugin</artifactId>
	<version>2.4</version>
	<configuration>
		<executions>
					<execution>
						<phase>prepare-package</phase>
						<goals>
							<goal>jar</goal>
						</goals>
						<configuration>
							<classesDirectory>${project.outputDirectory}</classesDirectory>
							<finalName>${artifactId.finalName}</finalName>
							<outputDirectory>${project.build.directory}/${project.artifactId}/WEB-INF/lib</outputDirectory>
							<includes>
					        	<include>com/XXX/XXX/**</include>
					       	</includes>
						</configuration>
					</execution>
		</executions>
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
~~~

## maven-antrun-plugin:
#### 在maven中运行Ant任务，比如在打包阶段，对文件进行复制
~~~
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
~~~

## wagon-maven-plugin:
#### 用于一键部署，把本地打包的jar文件，上传到远程服务器上，并执行服务器上的shell命令
~~~
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
~~~

## tomcat7-maven-plugin:
#### 用于远程部署Java Web项目
~~~
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <url>http://127.0.0.1:8080/manager/text</url>
        <username>tomcat-admin</username>
        <password>tomcat-admin</password>
    </configuration>
</plugin>
~~~


## tomcat-maven-plugin:
#### 用于远程部署Java Web项目
#### 增加的plugin节点用于pom.xml的server与maven的setting.xml中的id关联
~~~
<plugin>
	<groupId>org.codehaus.mojo</groupId>
    <artifactId>tomcat-maven-plugin</artifactId>
    <configuration>
    	<server>tomcat</server>
        <url>http://localhost:8080/manager/text</url>
        <path>/framework</path>
    </configuration>
</plugin>
~~~

##  maven-pmd-plugin
#### pmd插件
~~~
            <plugin>
                 <groupId>org.apache.maven.plugins</groupId>
                 <artifactId>maven-pmd-plugin</artifactId>
                 <version>3.8</version>
                 <configuration>
                     <rulesets>
                         <ruleset>rulesets/java/ali-comment.xml</ruleset>
                         <ruleset>rulesets/java/ali-concurrent.xml</ruleset>
                         <ruleset>rulesets/java/ali-constant.xml</ruleset>
                         <ruleset>rulesets/java/ali-exception.xml</ruleset>
                         <ruleset>rulesets/java/ali-flowcontrol.xml</ruleset>
                         <ruleset>rulesets/java/ali-naming.xml</ruleset>
                         <ruleset>rulesets/java/ali-oop.xml</ruleset>
                         <ruleset>rulesets/java/ali-orm.xml</ruleset>
                         <ruleset>rulesets/java/ali-other.xml</ruleset>
                         <ruleset>rulesets/java/ali-set.xml</ruleset>
                     </rulesets>
                     <printFailingErrors>true</printFailingErrors>
                 </configuration>
                 <executions>
                     <execution>
                         <goals>
                             <goal>check</goal>
                         </goals>
                     </execution>
                 </executions>
                 <dependencies>
                     <dependency>
                         <groupId>com.alibaba.p3c</groupId>
                         <artifactId>p3c-pmd</artifactId>
                         <version>1.3.0</version>
                     </dependency>
                 </dependencies>
             </plugin>
~~~

## cobertura-maven-plugin:
#### 测试覆盖率插件
~~~
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
~~~

## maven-shade-plugin:
#### 用于把多个jar包，打成1个jar包
#### 一般Java项目都会依赖其他第三方jar包，最终打包时，希望把其他jar包包含在一个jar包里

~~~
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
~~~

## maven-resources-plugin：
~~~
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
~~~

## build-helper-maven-plugin：
~~~
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
~~~

## maven-surefire-plugin：
~~~
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
~~~


## maven-assembly-plugin： 
#### maven自定义(修改)编译后输出的war或jar文件名
#### 支持定制化打包方式，负责将整个项目按照自定义的目录结构打成最终的压缩包，方便实际部署、可在此处设置打包拷贝路径，配置，以及打包好的jar文件等
~~~
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
~~~

## maven-dependency-plugin:
#### 用来拷贝项目所有依赖的插件,复制依赖的jar包到指定的文件夹里
~~~
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
~~~


## jspc-maven-plugin:
####  JSP 预编译插件  jspweb.xml
~~~
			<plugin>
				<groupId>org.jasig.mojo.jspc</groupId>
				<artifactId>jspc-maven-plugin</artifactId>
				<version>2.0.0</version>
				<configuration>
					<injectString>&lt;!- - [INSERT FRAGMENT HERE] - -&gt;</injectString>
				</configuration>
				<executions>
					<execution>
						<goals>
							<goal>compile</goal>
						</goals>
					</execution>
				</executions>
				<dependencies>
					<dependency>
						<groupId>org.jasig.mojo.jspc</groupId>
						<artifactId>jspc-compiler-tomcat6</artifactId>
						<version>2.0.0</version>
					</dependency>
				</dependencies>
			</plugin>
~~~

## jetty-jspc-maven-plugin:
####  Jetty JSP 预编译插件  web.xml
~~~
<plugin>
				<groupId>org.mortbay.jetty</groupId>
				<artifactId>jetty-jspc-maven-plugin</artifactId>
				<version>${jetty.version}</version>
				<configuration>
					<insertionMarker>&lt;!- - [INSERT FRAGMENT HERE] - -&gt;</insertionMarker>
				</configuration>
				<executions>
					<execution>
						<goals>
							<goal>jspc</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
~~~

## proguard-maven-plugin:
####  混淆代码
~~~
			<plugin>
				<groupId>com.github.wvengen</groupId>
				<artifactId>proguard-maven-plugin</artifactId>
				<version>2.0.11</version>
				<!-- <executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>proguard</goal>
						</goals>
					</execution>
				</executions> -->
				<configuration>
					<obfuscate>true</obfuscate>
                    <options>
			    		<!-- <option>-dontobfuscate</option> -->
                        <!--忽略所有告警-->
                        <option>-ignorewarnings</option>
                        <!--不做 shrink -->
                        <option>-dontshrink</option>
                        <!--不做 optimize -->
                        <option>-dontoptimize</option>
                        <!--保持包注解类-->
                        <option>-keep class **.package-info</option>

						<!-- 开始配置 -->
                        <!-- 以下为 Keep，哪些内容保持不变，因为有一些内容混淆后（a,b,c）导致反射或按类名字符串相关的操作失效 -->
                        <!--JAXB NEED，具体原因不明，不加会导致 JAXB 出异常，如果不使用 JAXB 根据需要修改-->
                        <option>-keepattributes Signature</option>

                        <!--保持源码名与行号（异常时有明确的栈信息），注解（默认会过滤掉所有注解，会影响框架的注解）-->
                        <option>-keepattributes SourceFile,LineNumberTable,*Annotation*</option>
						<!-- 结束配置 -->

						<!-- <option>-injars ${project.build.directory}/${artifactId.finalName}.war</option> -->
						<option>-injars ${project.build.directory}/${project.artifactId}/WEB-INF/lib/${artifactId.finalName}.jar</option>
                    </options>
                    <outjar>${project.artifactId}/WEB-INF/lib/${artifactId.finalName}-out.jar</outjar>

			       <!-- <options>
						<option>-injars ${project.build.directory}/${project.artifactId}/WEB-INF/lib/${artifactId.finalName}.jar</option>
                    </options>
                    <outjar>${project.artifactId}/WEB-INF/lib/${artifactId.finalName}-out.jar</outjar> -->


			        <!-- <options>
						<option>-injars ${project.build.directory}/${artifactId.finalName}.war</option>
                    </options>
                    <outjar>${project.artifactId}/${artifactId.finalName}-out.war</outjar> -->


                    <outputDirectory>${project.build.directory}</outputDirectory>
			        <proguardInclude>${basedir}/pom-proguard.cfg</proguardInclude>
					<source>${jdk.version}</source>
					<target>${jdk.version}</target>
					<encoding>${project.build.sourceEncoding}</encoding>
					<libs>
						<lib>${java.home}/lib/rt.jar</lib>
						<lib>${java.home}/lib/jsse.jar</lib>
			            <lib>${java.home}/lib/jce.jar</lib>
					</libs>
			        <addMavenDescriptor>false</addMavenDescriptor>
				</configuration>
				<dependencies>
					<dependency>
						<groupId>net.sf.proguard</groupId>
						<artifactId>proguard-base</artifactId>
						<version>5.3.3</version>
						<scope>runtime</scope>
					</dependency>
				</dependencies>
			</plugin>
~~~
#### pom-proguard.cfg
~~~
# -------------------------------------------------------------------------------------------------------------------------------------------------------------
# 1. 应该最大程度的熟悉项目的结构
# 2. 对于保留和非保留的代码应该注意代码之间的关联关系，防止保留的部分调用到未混淆部分而出现异常
# 3. 混淆后调试困难，有些问题不仅应该考虑混淆的问题，还应考虑proguard版本的问题（可能存在BUG）
# 4. 应当保留bean对象和action中的属性名称，防止jsp页面和action数据无法交互
# 5. 使用action的modeldriven 对象类型为List<T> 时，应当配置保留泛型
# 6. 采用annotation配置应该注意spring注入的方式是采用byName还是byType，防止因为代码混淆后无法按照指定的类型注入bean
# -------------------------------------------------------------------------------------------------------------------------------------------------------------

# ----------------------------------
#  通过指定数量的优化能执行
#  -optimizationpasses n
# ----------------------------------
-optimizationpasses 3

# ----------------------------------
#   混淆时不会产生形形色色的类名
#   -dontusemixedcaseclassnames
# ----------------------------------
-dontusemixedcaseclassnames

# ----------------------------------
#      指定不去忽略非公共的库类
#  -dontskipnonpubliclibraryclasses
# ----------------------------------
#-dontskipnonpubliclibraryclasses

# ----------------------------------
#       不预校验
#    -dontpreverify
#	 -dontoptimize
#java.lang.ArrayIndexOutOfBoundsException，解决办法：将proguard.cfg中的"-dontpreverify"改成“-dontoptimize”
# ----------------------------------
#-dontpreverify

# ----------------------------------
#      输出生成信息
#       -verbose
# ----------------------------------
-verbose

#混淆时应用侵入式重载
-overloadaggressively

#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#确定统一的混淆类的成员名称来增加混淆
-useuniqueclassmembernames

-dontwarn


#-keepdirectories  **
#-useuniqueclassmembernames

#忽略警告
-ignorewarnings

#keepattributes
-keepattributes **

#保持泛型
-keepattributes Signature

#保持源码名与行号（异常时有明确的栈信息），忽略注解（默认会过滤掉所有注解，会影响框架的注解）
-keepattributes SourceFile,LineNumberTable,*Annotation*
-keepattributes *Annotation*
#-keepattributes {attribute_name,...} 保护给定的可选属性，例如LineNumberTable, LocalVariableTable, SourceFile, Deprecated, Synthetic, Signature, and InnerClasses.

#java.lang.ClassFormatError: LVTT entry for 'a' in class file ×× does not match any LVT entry 这个貌似是Proguard的bug,使用下面的代码解决
-optimizations !code/allocation/variable


# -------------------------------------------------------------------------------------------------------------------------------------------------------------
#这里添加你不需要混淆的类
#org
-keep enum org.** {*;}
-keep class org.** {*;}
-keep public enum org.** {*;}
-keep public class org.** {*;}

#javax.servlet.Servlet
-keep class * extends javax.servlet.Servlet
-keep public class * extends javax.servlet.Servlet


#common
-keep public class com.xxx.xxx.xxx.config.LeSval
-keep enum com.xxx.xxx.xxx.license.common.service.bd.* {*;}
-keep class com.xxx.xxx.xxx.license.common.service.bd.* {*;}
-keep public enum com.xxx.xxx.xxx.license.common.service.bd.* {*;}
-keep public class com.xxx.xxx.xxx.license.common.service.bd.* {*;}
# -------------------------------------------------------------------------------------------------------------------------------------------------------------
-keep class * extends org.springframework.web.filter.GenericFilterBean {*;}
-keep public class * extends org.springframework.web.filter.GenericFilterBean {*;}

#接口
-keep interface com.xxx.xxx.xxx.license.common.service.** {*;}
-keep public interface com.xxx.xxx.xxx.license.common.service.** {*;}
-keepclassmembers interface com.xxx.xxx.xxx.license.common.service.* {
    public protected private <fields>;
}
-keepclassmembers interface com.xxx.xxx.xxx.license.common.service.* {
	public protected private <methods>;
}
-keepclassmembers public interface com.xxx.xxx.xxx.license.common.service.* {
    public protected private <fields>;
}
-keepclassmembers public interface com.xxx.xxx.xxx.license.common.service.* {
	public protected private <methods>;
}

#注解
-keep @interface com.xxx.xxx.xxx.license.common.service.** {*;}
-keep public @interface com.xxx.xxx.xxx.license.common.service.** {*;}
-keepclassmembers @interface com.xxx.xxx.xxx.license.common.service.* {
    public protected private <fields>;
}
-keepclassmembers public @interface com.xxx.xxx.xxx.license.common.service.* {
    public protected private <fields>;
}

# -------------------------------------------------------------------------------------------------------------------------------------------------------------
#modules.le
#-keep enum com.xxx.xxx.xxx.license.modules.le.** {*;}
#-keep class com.xxx.xxx.xxx.license.modules.le.** {*;}
#-keep public enum com.xxx.xxx.xxx.license.modules.le.** {*;}
#-keep public class com.xxx.xxx.xxx.license.modules.le.** {*;}

# -------------------------------------------------------------------------------------------------------------------------------------------------------------
#-keepnames class * implements java.io.Serializable
# ---------保护所有实体中的字段名称----------
-keepclassmembers class * implements java.io.Serializable {
    public protected private <fields>;
}

# --------- 保护类中的所有方法名 ------------
-keepclassmembers class * {
	public protected private <methods>;
}

# --------- 保护类中的所有成员 --------------
#-keep
#-keepnames
#-keepclassmembers
#-keepclasseswithmembers
~~~

## maven-eclipse-plugin:
#### Eclipse 插件
~~~
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-eclipse-plugin</artifactId>
				<version>2.9</version>
				<configuration>
					<downloadSources>${downloadSources}</downloadSources>
					<downloadJavadocs>false</downloadJavadocs>
					<wtpversion>2.0</wtpversion>
					<jeeversion>5.0</jeeversion>
					<!-- <jeeversion>6.0</jeeversion> -->
					<additionalConfig>
						<file>
							<name>.settings/org.eclipse.core.resources.prefs</name>
							<content>
								<![CDATA[eclipse.preferences.version=1${line.separator}encoding/<project>=${project.build.sourceEncoding}${line.separator}]]>
							</content>
						</file>
					</additionalConfig>
					<additionalProjectnatures>
						<projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
					</additionalProjectnatures>
				</configuration>
			</plugin>

			<!-- tomcat6插件 -->
			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat6-maven-plugin</artifactId>
				<version>${tomcat.version}</version>
				<configuration>
					<port>${webserver.port}</port>
					<path>/${project.artifactId}</path>
					<uriEncoding>${project.build.sourceEncoding}</uriEncoding>
				</configuration>
			</plugin>
~~~

## tomcat7-maven-plugin:
#### tomcat7插件
~~~
			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat7-maven-plugin</artifactId>
				<version>${tomcat.version}</version>
				<configuration>
					<port>${webserver.port}</port>
					<path>/${project.artifactId}</path>
					<uriEncoding>${project.build.sourceEncoding}</uriEncoding>
				</configuration>
			</plugin>
~~~

## jetty-maven-plugin:
#### jetty插件
~~~
			<plugin>
				<groupId>org.mortbay.jetty</groupId>
				<artifactId>jetty-maven-plugin</artifactId>
				<version>${jetty.version}</version>
				<configuration>
					<connectors>
						<connector implementation="org.eclipse.jetty.server.nio.SelectChannelConnector">
							<port>${webserver.port}</port>
						</connector>
					</connectors>
					<webAppConfig>
						<contextPath>/${project.artifactId}</contextPath>
					</webAppConfig>
					<systemProperties>
						<systemProperty>
							<name>org.mortbay.util.URI.charset</name>
							<value>${project.build.sourceEncoding}</value>
						</systemProperty>
					</systemProperties>
				</configuration>
			</plugin>
~~~




			<!-- sonar插件 -->
			<plugin>
		        <groupId>org.codehaus.mojo</groupId>
		        <artifactId>sonar-maven-plugin</artifactId>
		        <version>2.7.1</version>
		    </plugin>
