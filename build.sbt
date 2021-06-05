ThisBuild / organization := "com.github.sps"
ThisBuild / scalaVersion := "2.13.6"

ThisBuild / developers :=
  Developer("sps", "Sean Scanlon", "sean.scanlon@gmail.com", url("http://github.com/sps")) ::
  Developer("extant", "Barry Loper", "barry@loper.cc", url("http://github.com/extantpedant")) ::
  Nil

ThisBuild / licenses := Seq("Apache License 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    browseUrl=url("http://github.com/extantpedant/metrics-opentsdb"),
    connection="scm:git:git://github.com/extantpedant/metrics-opentsdb.git",
    devConnection="scm:git:git@github.com:extantpedant/metrics-opentsdb.git"
  )
)

lazy val sourceEncoding = "UTF-8"
lazy val outputEncoding = "UTF-8"
lazy val metricsVersion = "3.1.0"
lazy val jerseyVersion = "2.22.2"
lazy val mockitoVersion = "1.10.17"
lazy val slf4jVersion = "1.7.5"
lazy val junitVersion = "4.12"

lazy val MetricsOpentsdb = (project in file("."))
  .settings(
    name := "metrics-opentsdb",
    description := "metrics reporter for OpenTSDB",
    homepage := Some(url("http://github.com/sps/metrics-opentsdb")),
    Compile / javacOptions ++= Seq("-deprecation", "-source", "8", "-target", "8"),
    // Include only src/main/java in the compile configuration
    Compile / unmanagedSourceDirectories := (Compile / javaSource).value :: Nil,
    // Include only src/test/java in the test configuration
    Test / unmanagedSourceDirectories := (Test / javaSource).value :: Nil,
    artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
      artifact.name + "-" + module.revision + "." + artifact.extension
    },
    libraryDependencies ++= Seq(
      "io.dropwizard.metrics" % "metrics-core" % metricsVersion,
      "org.glassfish.jersey.core" % "jersey-client" % jerseyVersion,
      "org.glassfish.jersey.media" % "jersey-media-json-jackson" % jerseyVersion,
    ),
    Test / libraryDependencies ++= Seq(
      "org.mockito" % "mockito-core" % mockitoVersion exclude("org.hamcrest", "hamcrest-core"),
      "org.slf4j" % "slf4j-simple" % slf4jVersion
    )
  )

/**todo: find alternatives to these maven plugins
 *             <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <version>2.10.4</version>
                <executions>
                    <execution>
                        <id>attach-javadocs</id>
                        <goals>
                            <goal>jar</goal>
                        </goals>
                        <configuration>
                            <additionalparam>-Xdoclint:none</additionalparam>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>findbugs-maven-plugin</artifactId>
                <version>3.0.0</version>
                <configuration>
                    <effort>Max</effort>
                    <threshold>Default</threshold>
                    <xmlOutput>true</xmlOutput>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>check</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.7.2.201409121644</version>
                <executions>
                    <execution>
                        <id>default-prepare-agent</id>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>default-report</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>default-check</id>
                        <goals>
                            <goal>check</goal>
                        </goals>
                        <configuration>
                            <rules>
                                <!-- implmentation is needed only for Maven 2 -->
                                <rule implementation="org.jacoco.maven.RuleConfiguration">
                                    <element>BUNDLE</element>
                                    <limits>
                                        <!-- implmentation is needed only for Maven 2 -->
                                        <limit implementation="org.jacoco.report.check.Limit">
                                            <counter>COMPLEXITY</counter>
                                            <value>COVEREDRATIO</value>
                                            <minimum>0.90</minimum>
                                        </limit>
                                    </limits>
                                </rule>
                            </rules>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.eluder.coveralls</groupId>
                <artifactId>coveralls-maven-plugin</artifactId>
                <version>3.0.1</version>
                <configuration>
                    <repoToken>${COVERALLS_REPO_TOKEN}</repoToken>
                </configuration>
            </plugin>
        </plugins>
    </build>
 */
