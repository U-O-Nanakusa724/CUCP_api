# そもそもgradleとタスクって？
プロジェクトやライブラリの依存関係定義を行うほか、自作のミニメソッド(タスクという)を持つことができる。\
ライブラリのバージョン管理や開発環境を簡単に整えるなどに貢献する。  
とりあえず[ここ](https://qiita.com/opengl-8080/items/a0bb31fb20cb6505188b)を読めば大体書けるようになるかと。

## gradleの大まかなブロックの役割

buildsqript : このプロジェクトの根幹を定義する。

plugin : このbuild.gradle上で利用したいライブラリを書く。  
例：flyway関連やeclipseのオプションに値を渡したい場合

dependences : プログラム中でimportしたいライブラリを書く。  
例：JPQLやSpringBootに相性のいいライブラリ(Thymeleaf, JPQL, Validatorなど)

## build.gradleに定義したタスク

bootJar : jarファイルを作成する
```groovy
bootJar {
    group = 'biz.uoray'
    jar.baseName = 'cucp'
    sourceCompatibility = 1.8
}

bootJar {
    doLast {
        copy {
            from "${buildDir}/libs/cucp.jar"
            from "${projectDir}/libs/jetty-alpn-agent-2.0.9.jar"
            into "deploy/"
        }
    }
}
```

flyway : flywayの各コマンド実行時に必要な情報をymlから取得する

```groovy
flyway {
	def applicationYaml = 'application-dev.yml'

	if (project.hasProperty('env') && project.env.matches(/^(local|dev|pro)$/)) {
		applicationYaml = "application-" + project.env + ".yml"
	} else {
		System.out.println("環境を指定して実行してください -Penv=local/dev/pro")
		return
	}

	def cfg = new Yaml().load(new File("src/main/resources/${applicationYaml}").newInputStream())
	url = cfg.spring."flyway-settings".url
	user = cfg.spring."flyway-settings".user
	password = cfg.spring."flyway-settings".password
	schemas = ['cucp']
	locations = ['filesystem:./flyway/sql']
}
```

dbSeed : 対象環境にサンプルデータを流し込む(未定義)