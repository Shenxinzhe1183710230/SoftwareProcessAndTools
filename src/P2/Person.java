package P2;

public class Person {//Person是基本的对象类型
	 private final String name;
	 
	 public Person(String friend) {
		 this.name=friend;
	 }
	 
	 public String getnameString() {//获取该Person的名字
		 return this.name;
	 }
	 
}
