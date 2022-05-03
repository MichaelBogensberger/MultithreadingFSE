package _20220419_Executor_Generics;

public class _03_Generics {

    public static void main(String[] args) {
        System.out.println("Hello World");

        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.toString());
    }
    
}

class MyLinkedList<D> {

    public Element<D> firstElement;

    public void add(D data){
        Element<D> newElement = new Element<>(data);
        if(firstElement == null){
            firstElement = newElement;
        }
        else {
            Element<D> lastElement = lastElement();
            lastElement.nextElement = newElement;
        }

    }

    public Element<D> lastElement(){
        Element<D> currentElement = firstElement;
        while(currentElement.nextElement != null){
            currentElement = currentElement.nextElement;
        }
        return currentElement;
    }

    @Override
    public String toString(){
        String returnString = "";
        Element<D> currentElement = firstElement;
        while(currentElement != null) {
            returnString += currentElement.data;
            returnString += "\n";
            currentElement = currentElement.nextElement;
        }
        return returnString;
    }

}

class Element<D> {
    public D data;
    public Element<D> nextElement;

    public Element(D data){
        this.data = data;
    }
}