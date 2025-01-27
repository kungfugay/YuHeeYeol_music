package com.mc.recommend.presentation;

import com.mc.recommend.coordinator.Coordinator;
import com.mc.recommend.domain.song.SongMessage;
import com.mc.recommend.infra.error.CommonException;
import com.mc.recommend.infra.error.code.IllegalGeminiResponseException;

import java.util.Scanner;

public class Menu {
    public void main() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("당신의 상황을 말해주세요. 어울리는 노래를 추천해드려요! (종료: exit) ");
                String input = sc.nextLine();
                if (input.equals("exit")) break;

                Coordinator coordinator = new Coordinator();
                SongMessage songMessage = coordinator.coordinate(input);

                System.out.println("추천하는 곡은 '" + songMessage.getTitle() + "' 입니다.");
                System.out.println(songMessage.getReason());
                System.out.println("생성된 QR코드를 통해 음악을 감상하세요!");
                
                // 우리가 만든 예외를 처리하는 모듈
            } catch (IllegalGeminiResponseException e){

                System.out.println(e.getMessage());

            } catch (CommonException e){

                System.out.println(e.getMessage());
            }
        }

    }
}
