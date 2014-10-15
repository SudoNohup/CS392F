package LApp; 

public  class  Entity {
	

    public static Entity[] entArray1 = {new Entity("Don", 60), 
	new Entity("Steve", 90), new Entity("Chief", 3),
        new Entity("Scarlett", 7), new Entity("Beth", 22), 
	new Entity("Chili", 20)};

	

    public static Entity[] entArray2 = {new Entity("Haggis", 1), 
	new Entity("Kelsey", 25)};

	

    String name;

	
    int age;

	

    public Entity(String name, int age) {
        this.name = name;
        this.age = age;
    }

	

    public String toString() {
        return "(" + name + ", " + age + ")";
    }


}
