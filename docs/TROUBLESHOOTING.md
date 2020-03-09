# Troubleshooting

1. `SpatialTest` extends ScalaTest which can be run in parallel managed by their sbt plugin. If your app is an `SpatialTest`, you must have an assertion check in your app, orelse you will get the following error. 
```
 argon.UnhandledException: Uncaught exception Test had no validation checks. (Indeterminate result) (null)   
```

2. When you start sbt, it first looks at your `project/build.properties` for a sbt version. If the version you installed doesn't match the required version in your `build.sbt`, it will download the required version in `~/.sbt`. Then it looks for library dependencies and download them to your local ivy cache in `~/.ivy2`. Sometimes the cached version can be corrupted. You can remove the the wrong sbt in `~/.sbt` or the problematic library in `~/.ivy2/cache/libpath`. 

3. If you see this error
```
 java.lang.NoClassDefFoundError: sourcecode/Name 
```
or 
```
[error] sbt.internal.inc.InvalidScalaInstance: Scala instance doesn't exist or is invalid:                                                                               
[error]     version 2.12.10, library jar: /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/org.scala-lang/scala-library/scala-library-2.12.10.jar, compiler jar: /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/org.scala-lang/scala-compile
r/scala-compiler-2.12.10.jar, other jars: /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/org.scala-lang/scala-reflect/scala-reflect-2.12.10.jar, /home/yaqiz/pldi20/spatial/pir/lib_managed/bundles/org.scala-lang.modules/scala-xml_2.12/scala-xml_2.12-1.0.6.jar, /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/jline/jline/jline-2.14.6.jar, /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/org.fusesource.jansi/jansi/jansi-1.12.jar
```
Or error relates to `AbstractMethod` or other `ClassDefNotFoundError` during genpir pass, recompile pir and try again. 
```
cd spatial/pir
make clean-local
make pir
```

4. If you see errors saying something related to `syntax error EOF`. That's outdated emptyness dependency. Remove your `~/bin/emptyness*` and rerun again. You need to have `pkg-config`, which spatial uses to install `emptyness` dependency.

5. If you see an error like this when starting sbt
```
SERVER ERROR: HTTPS Required url=http://repo1.maven.org/maven2/org/scala-sbt/sbt/1.1.1/sbt-1.1.1.jar
...
unresolved dependency: org.scala-sbt#sbt;1.1.1: not found
...
```
Try `cp spatial/pir/bin/repositories ~/.sbt/repositories` and load sbt again. 
This repo uses sbt 1.1.1. If you have a old version of sbt, the default repositories uses http, which they now moved to https. This fix manually adds new urls for repositories in `~/.sbt/repositories`, which uses https. 
Sbt then should download the required sbt version and you can remove the file once sbt 1.1.1 is downloaded. 
