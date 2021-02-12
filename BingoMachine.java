//乱数
import java.util.Random;
//入力
import java.util.Scanner;
//リスト
import java.util.List;
import java.util.ArrayList;

/*
・最初に数を入力
・その数以下の正数を生成

・数を1つ表示
・既出の全ての数とその個数を表示
・次の数を表示するか終了する
・数がなくなるまで繰り返す

・数がなくなれば終了

*/
public class BingoMachine {
    //フィールド
    private static int maxNumber;
    private static List<Integer> allNumber;

    //number以下の乱数を表示
    public BingoMachine(int number){
        allNumber = new ArrayList<Integer>();
        maxNumber = number;
    }

    //乱数ジェネレータ
    public static int getRandomNumber(){
        Random rand = new Random();
        int ans = 1 + rand.nextInt(maxNumber);
        return ans;
    }
    //既出か探索し、既出ならfalse
    public static boolean checkAlreadyNumber(int number){
        boolean ans=false;
        //見つからない（未出）
        if(allNumber.indexOf(number) <= -1){    
            ans = true;
        }
        return ans;
    }
    //既出の数を表示
    public static void printAlreadyNumber(){
        System.out.println("既出の数");
        System.out.print("{");
        for(int i=0;allNumber.size()-1 > i;i++){
            System.out.print(allNumber.get(i) + ",");
        }
        System.out.print(allNumber.get(allNumber.size()-1));
        System.out.println("}");
    }
    //残りの個数を表示
    public static void printRemainigQuantity(){
        System.out.println("残り個数は" +(maxNumber - allNumber.size())+"です");
    }

    //メイン
    public static void main(String[] args){
        //オブジェクトの生成
        Scanner scan = new Scanner(System.in);
        //数の入力
        System.out.println("数を入力してください");
        //0以上の数が入力されるまで繰り返す
        //三項条件演算子
        int tmp=0;
        do{
            tmp = scan.nextInt();
        }while((tmp <= 0) ? true : false );

        //オブジェクトの作成
        BingoMachine bm = new BingoMachine(tmp);

        int currentNumber;
        do{
            do{
                //乱数を生成
                currentNumber = bm.getRandomNumber();
                //既出か探索
                //既出なら再生成
            }while(! bm.checkAlreadyNumber(currentNumber));
            //リストに数を代入
            allNumber.add(currentNumber);

            //数を表示
            System.out.println(currentNumber);
            //既出の全ての数を表示
            bm.printAlreadyNumber();
            //数の個数を表示
            bm.printRemainigQuantity();

            //最後の数
            if(maxNumber == allNumber.size()){
                break;
            }

            //続けるか質問
            System.out.println("終了するなら0、続けるなら1を入力");
            tmp = scan.nextInt();
            if(tmp == 0)
                break;
            System.out.println("************************");
            //数がなくなるまで繰り返す
        }while(maxNumber - allNumber.size()-1 >= 0 ? true : false);

        System.out.println("終了します");

    }
}
