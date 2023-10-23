package hello.core.sington;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드 (무상태를 위해 주석처리)

//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 문제부분!!!!!

    // 스프링빈은 무상태로 설정해야한다!!!
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 문제부분!!!!!
        return price;
    }


//    public int getPrice() {
////        return price; (무상태를 위해 x)
//    }
}
