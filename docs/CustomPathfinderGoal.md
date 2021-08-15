# Pathfindergoals​ を自作する

[このページ](https://www.spigotmc.org/threads/tutorial-creating-custom-entities-with-pathfindergoals.18519/)の翻訳です。

Pathfindergoals はあなたの Entity の AI を操作します。

Pathfindergoal を作成するには、 次のようなクラスを作ってください:​

```java
public class PathfinderGoalWalkToLoc extends PathfinderGoal {

}
```

Pathfindergoal を継承してることを確認してください。

さて、IDE を使用している方はエラーが出ていることでしょう。（少なくとも私の使っている IntelliJ はそうです。）これは、クラスに実装しなければいけないメソッドがあるからです。

```java
public class PathfinderGoalWalkToLoc extends PathfinderGoal {

  public boolean a() {
    return true;
  }

}
```

このメソッドは`shouldStart()`を意味します

次に、`c()`というメソッドを追加します。これは`a()`が`true`を返したときに呼ばれるメソッドです。(なので、常に`c()`を実行したいときは、上のコードでやったように`a()`で`true`を返すようにしてください)。私たちはエンティティをある地点に移動させたいので、まずコンストラクタで`Entity`と`Location`のフィールドを初期化します。

```java
public class PathfinderGoalWalkToLoc extends PathfinderGoal {

  private double speed;

  private EntityInsentient entity;

  private Location loc;

  private Navigation navigation;

  public PathfinderGoalWalkToLoc(EntityInsentient entity, Location loc, double speed) {
    this.entity = entity;
    this.loc = loc;
    this.navigation = this.entity.getNavigation();
    this.speed = speed;
  }

  public boolean a() {
    return true;
  }

}
```

さて、いよいよ`c()`を追加していきます。`c()`は `onStart()`を意味します。`a()`が一度`true`を返すと、このメソッドが実行されます。

```java
public void c(){
  PathEntity pathEntity = this.navigation.a(loc.getX(), loc.getY(), loc.getZ());
  this.navigation.a(pathEntity, speed);
}

```

難読化されたメソッドと、各メソッドの意味について

```java

public boolean a() {
  //should start
}

public boolean b() {
  //perhaps should continue pathfinding
}

public void c() {
  //on start pathfinding
}

public void e() {
  //run every single tick while this goal is pathfinding
}

public void d() {
  //on exit pathfinding
}


```

`Location`と`Entity`が 20 ブロック以上離れていないことを確認してください。（詳しくありませんが、恐らく 20 ブロック）。もしそれ以上離れていると`pathEntity`フィールドが null になります。
