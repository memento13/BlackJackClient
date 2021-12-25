import entity.MsgParameters;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = null;
        String passcode=null;
        if(args[0] ==null){
            System.out.println("실행시 host를 입력해주세요");
            System.exit(0);
        } else{
            host = args[0];
        }

        if(args[1] ==null){
            System.out.println("실행시 host와 password를 입력해주세요");
            System.exit(0);
        } else{
            passcode = args[1];
        }
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try{
            socket = new Socket(host, 7890);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());


            outputStream.writeUTF(passcode);
            outputStream.flush();

            String inputMsg = inputStream.readUTF();
            MsgParameters msgParameters = new MsgParameters(inputMsg);

            System.out.println("상태코드 : "+msgParameters.get("code"));
            System.out.println("메시지 : "+msgParameters.get("msg"));
            Scanner sc = new Scanner(System.in);
            String answer;

            if(msgParameters.get("code").equals("200")){
                boolean alive = true;
                while (alive){
                    inputMsg = inputStream.readUTF();

                    msgParameters = new MsgParameters(inputMsg);
                    int code = Integer.parseInt(msgParameters.get("code"));

                    if(code == 300){ //계좌금액 지급
                        System.out.println("상태코드 : "+msgParameters.get("code"));
                        System.out.println("메시지 : "+msgParameters.get("msg"));

                        System.out.println("현재 내 보유잔고 : "+msgParameters.get("account"));

                        answer = sc.nextLine();
                        outputStream.writeUTF(answer);
                        outputStream.flush();
                    }
                    else if(code ==301){ //배팅금액이 계좌보다 큰 경우
                        System.out.println("상태코드 : "+msgParameters.get("code"));
                        System.out.println("메시지 : "+msgParameters.get("msg"));
                        System.out.println("현재 내 보유잔고 : "+msgParameters.get("account"));

                        answer = sc.nextLine();
                        outputStream.writeUTF(answer);
                        outputStream.flush();
                    }
                    else if(code == 302){ //배팅금액이 500보다 큰 경우
                        System.out.println("상태코드 : "+msgParameters.get("code"));
                        System.out.println("메시지 : "+msgParameters.get("msg"));
                        System.out.println("현재 내 보유잔고 : "+msgParameters.get("account"));

                        answer = sc.nextLine();
                        outputStream.writeUTF(answer);
                        outputStream.flush();
                    }
                    else if(code ==303){ //배팅성공
                        System.out.println("상태코드 : "+msgParameters.get("code"));
                        System.out.println("메시지 : "+msgParameters.get("msg"));
                    }
                    else if(code ==500){ //플레이어 블랙잭
                        System.out.println("상태코드 : "+msgParameters.get("code"));
                        System.out.println("메시지 : "+msgParameters.get("msg"));

                        System.out.println("현재 내 보유잔고 : "+msgParameters.get("account"));

                        System.out.println("내카드");
                        String cards = msgParameters.get("client_cards");
                        String[] split = cards.split("&");
                        for (String card : split) {
                            System.out.println(card+" ");
                        }
                        System.out.println();
                        System.out.println("===========================");
                        System.out.println("그만할려면 quit 입력");

                        answer = sc.nextLine();
                        outputStream.writeUTF(answer);
                        outputStream.flush();
                        if(answer.equals("quit")){
                            alive = false;
                        }
                    }
                    else if(code==501){ //딜러 블랙잭
                        System.out.println("상태코드 : "+msgParameters.get("code"));
                        System.out.println("메시지 : "+msgParameters.get("msg"));

                        System.out.println("딜러카드");
                        String cards = msgParameters.get("dealer_cards");
                        String[] split = cards.split("&");
                        for (String card : split) {
                            System.out.println(card+" ");
                        }
                        System.out.println();
                        System.out.println("===========================");
                        System.out.println("그만할려면 quit 입력");

                        answer = sc.nextLine();
                        outputStream.writeUTF(answer);
                        outputStream.flush();
                        if(answer.equals("quit")){
                            alive = false;
                        }
                    }
                    else if(code ==510){ //플레이어 승리
                        System.out.println("상태코드 : "+msgParameters.get("code"));
                        System.out.println("메시지 : "+msgParameters.get("msg"));

                        System.out.println("딜러카드");
                        String cards = msgParameters.get("dealer_cards");
                        String[] split = cards.split("&");
                        for (String card : split) {
                            System.out.println(card+" ");
                        }System.out.println();

                        System.out.println("내카드");
                        cards = msgParameters.get("client_cards");
                        split = cards.split("&");
                        for (String card : split) {
                            System.out.println(card+" ");
                        }
                        System.out.println();

                        System.out.println("현재 내 보유잔고 : "+msgParameters.get("account"));
                        System.out.println("===========================");
                        System.out.println("그만할려면 quit 입력");

                        answer = sc.nextLine();
                        outputStream.writeUTF(answer);
                        outputStream.flush();
                        if(answer.equals("quit")){
                            alive = false;
                        }

                    }
                    else if(code==520){

                        System.out.println("상태코드 : "+msgParameters.get("code"));
                        System.out.println("메시지 : "+msgParameters.get("msg"));

                        System.out.println("딜러카드");
                        String cards = msgParameters.get("dealer_cards");
                        String[] split = cards.split("&");
                        for (String card : split) {
                            System.out.println(card+" ");
                        }System.out.println();

                        cards = msgParameters.get("client_cards");
                        split = cards.split("&");
                        System.out.println("내카드");
                        for (String card : split) {
                            System.out.println(card+" ");
                        }
                        System.out.println();

                        System.out.println("현재 내 보유잔고 : "+msgParameters.get("account"));
                        System.out.println("===========================");
                        System.out.println("그만할려면 quit 입력");

                        answer = sc.nextLine();
                        outputStream.writeUTF(answer);
                        outputStream.flush();
                        if(answer.equals("quit")){
                            alive = false;
                        }
                    }
                    else if(code==800){
                        System.out.println("상태코드 : "+msgParameters.get("code"));
                        System.out.println("메시지 : "+msgParameters.get("msg"));
                        System.out.println("딜러카드");

                        String first_card = msgParameters.get("dealer_first_card");
                        System.out.println(first_card + " pattern= ?? , denomination= ??");

                        String cards = msgParameters.get("client_cards");
                        String[] split = cards.split("&");
                        System.out.println("내카드");
                        for (String card : split) {
                            System.out.println(card+" ");
                        }
                        System.out.println();

                        answer = sc.nextLine();
                        outputStream.writeUTF(answer);
                        outputStream.flush();

                    }

                    System.out.println("===========================");


                }

            }
            else{
                System.out.println("연결이 되지 않았습니다!");
            }

            inputStream.close();
            outputStream.close();
            socket.close();

        }catch (Exception e){
            System.out.println("오류발생!");
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
            if(socket != null){
                socket.close();
            }
        }
    }

}
