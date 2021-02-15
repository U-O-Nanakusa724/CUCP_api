# 競合発生時の回避方法ーgit stash 編ー

ローカルの開発で「失いたくはないけどコミットしたくもない」  
編集内容が存在するときにpull しやすくする方法。

1. 下記コマンドで編集内容を退避する
```cmd
git stash
```
2. 正しく退避できたか確認する
```cmd
git stash list
stash@{0} ---直近の編集内容などの情報---
上記が表示されていればOK
```

3. pullなど行う
4. 退避させたものを戻す
```
git stash apply stash@{0}
```
5. 退避データを削除する
```cmd
git stash drop stash@{0}
```
applyしただけでは(自動では)退避情報は消えない。  
今後また退避させたときにマージミスや誤消去を避けるために１回stashする度に  
apply, dropするクセをつけておくこと。


# commitログの統一ルール ースリーライン形式ー

下記をフォーマットとする。

```cmd
git commit -m "[ジャンル] [ブランチ名]ブランチ概要" -m "具体的な修正内容"
```

例

```cmd
git commit -m "[add]feature/cucp-X ○○機能作成" -m "コントローラを実装"
```

[ ]タグの例
- add     ：新規ファイルの追加
- update  ：バグでない改修内容
- fix     ：バグや不具合修正
- hotfix  ：fixの中で特に緊急性の高いもの
- remove  ：ファイルの削除
- clear   ：リファクタリング

参考サイト:https://qiita.com/itosho/items/9565c6ad2ffc24c09364