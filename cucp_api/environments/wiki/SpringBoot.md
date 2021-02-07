# SpringBootに関する共有事項など

## 基本設計

Java : Java 11\
変数・関数の命名規則 : ローワーキャメルケース。  
リポジトリ : JpaRipositoryを使用する(他にメジャーなのはCrudRipositoryなど)  
設定ファイル : application-[local/dev/pro].yml で定義する。

## 本SpringBootプロジェクトで採用するディレクトリ構造と役割

```cmd
main
   -java
      -constant    ：Enumやアプリ内定数をあらかじめ定義したクラスを管理する。コンフィグ読み込みクラスもここ。
      -controller  ：フロントからのリクエストを受け取り、model層やview層へデータを渡す。
      -dto         ：データ受け渡し専用クラスを置く。
      -form        ：フォームに入力された値に対しバリデーションチェックを行う。
      -entity      ：DBのテーブルに対応する受け皿クラスを格納する。
      -exeption    ：ユーザ定義例外処理クラスを格納する。
      -repositry   ：DBへのアクセスを行うクラス。このディレクトリ直下にはデフォルトのO/Rマッパーを利用するものを置く。
         -custom   ：リポジトリのうち、自作したい場合はここに『インタフェース』を定義する。
         -impl     ：上記インタフェースを実装するクラス。
      -request     ：Formクラスと似ているが、こちらはJsonで受け取ったオブジェクトにバリデーションチェックを行いつつEntityに変換する。
      -response    ：JsonPropertyアノテーションを利用するクラスをここに置く。
         -inline   ：Json内に入れるJsonを作るクラスをここに置く。
      -security    ：認証やセッション、FireBase認証確認などセキュリティ関連のロジックを置く。
      -service     ：コントローラ及びモデルの中間層。コントローラのモデル化やモデルの肥大化を回避する。
      -util        ：アプリ内で共通で利用することが多いメソッドをstatic定義してここに置く。
   -resources      ：application.ymlやconfigのデフォルト参照先。
      -static
         -css      ：cssファイルのデフォルト参照先。
         -js       ：jsファイルのデフォルト参照先。
      -templates   ：htmlファイルのデフォルト参照先。この配下にディレクトリを切り、相対パスで指定する。

```

## SpringBootの便利機能やルールなど

- 命名にローワーキャメルケースを利用する理由

@Data @JsonNaming アノテーションを最大限に利用するため。  
特に@JsonNaming は文字の大小を自己判断しJavaScriptなどフロント側が扱いやすいスネークケースへ変換する優れもの。

- デバッグ・デプロイ時の環境変数ファイル指定

VMオプションに下記を指定することで、設定ファイルを書き換えることなくDBの向き先を変更可能。

```cmd
-Dspring.profiles.active=[dev / pro]
```

