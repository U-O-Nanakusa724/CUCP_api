# JMeterのダウンロード〜起動まで

A. サイトからzip(tar)をダウンロードする場合

１. https://jmeter.apache.org/download_jmeter.cgi からダウンロード
２. 任意のフォルダで展開
３. apache-jmeter-X.X.X/bin/ApacheJMeter.jarを起動

＊「開発者不明のため起動できません」と出た場合は、Ctrlを押しながら右クリック→開くで開けるようにすること。
＊jarを直接起動できない場合、「jmeter」という名前のファイル(mac上で拡張子がないもの)を押下しても起動可能。

B. homebrewを通してダウンロードする場合

１. brew install jmeter
２. インストールを待つ
３. jmeter
４. JMeterのGUIが出ればOK

＊brewを通した方法だと何故かJMeterのテストが保存できなかったので、以下Aの前提で進める。

## JMeter言語切り替え

Options > Choose Language > Jananese

＊以下、言語は英語のままであるとして進める。

# テスター作成

１. 左のシナリオ欄にあるTest Planを右クリック(Test Planの名前は任意に変えてOK)
２. Add > Threads(User) > Thread Group を押下
３. Thread GroupのNameやCommentsは任意に編集
４. Thread Propertiesを編集する

- Number of Threads(users): 立ち上げるスレッド数≒アクセス数、同時接続数
- Ramp-up period(seconds): どれくらいの時間をかけて全スレッドを終わらせるかの指定
- Loop Count: 上記セットを何回行うか

＊「Delay Thread creation until needed」にチェックを入れること。
でないと試験開始直後にスレッド数で指定した数だけスレッドが出来てしまい、負荷をかける側のメモリ圧迫などで試験が正常に動作しなくなる可能性がある。

５. 作成したThread Groupを右クリックし、addから以下を選択

- Add > Sampler > HTTP Request: HTTPリクエストの設定を行う。

＊以下２つはGUI上で見るために入れるもの。

- Add > Listener > View Results Tree: テスト実行結果を表示。こちらは出したリクエストと受け取ったレスポンスの確認がしやすい。
- Add > Listener > View Results in Table: テスト実行結果を表示。こちらは登録したテストたちの結果を一見しやすい。

６. HTTP Requestについて、以下の項目を適宜変更

- Protocol: httpかhttpsか、試験先に合わせて指定。
- Server Name or IP: 文字通り、試験先のIPアドレス。ローカルならlocalhostもOK。
- Port Number: こちらも試験先に合わせて指定。特になければ空白。
- HTTP Request: 試験するHTTPメソッドとURLを記入する。
- Content encoding: 特に後述のBodyDataを利用する場合、ここにUTF8を入力する。
- Parameters: リクエストパラムをここに設定する。
- Body Data: リクエストボディを利用する場合ここにJsonを記入。

７. 作成したHTTP Requestを右クリックし、Add > Config Element > HTTP Header Manager を選択

８. HTTP Header Managerについて、以下を設定(順不同)

| Name | Value |
| :--: | :---: |
| Content-Type | application/json |
| accept | application/json |
| id_token | (取得したid_token) |

＊acceptいらないかもしれない

９. Startを押下し、StatusがSuccessなら成功

# 正規表現でレスポンスから値を取り出し利用する

１. 取り出したい値がレスポンスに含まれるHTTP Requestを右クリックし、Add > Post Prossesor > Regular Expression Extractorを選択

２. Regular Expression Extractorを編集

- Name of created variable: 格納する変数名。
- Regular Expression: 正規表現。丸括弧内が抽出対象。
- Template: 引っかかったもののうち何番目を取得するか。１番目であれば$1$。
- Match: 何回目に引っかかった正規表現を取得するか。 
- Default Value: 正規表現で取得できなかった場合のデフォルト値。右のチェックを入れると空を指定できるが、明白なデフォルト値を指定しておくとデバッグしやすい。

３. 以後、利用したい箇所で${Name of created variableで定めた変数名}とすると利用可能。


# 便利なメソッド

１. 時間(タイムゾーンあり)を動的に生成してリクエストに含めたい場合

下記のようにtimeメソッドを利用する。

    ${__time(yyyy-MM-dd'T'HH:mm:ss.SSSZ)}

