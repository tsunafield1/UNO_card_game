# UNO_card_game
โปรเจคนี้มีชื่อว่า "UNO card game" ทำขึ้นเพื่อสร้างเกมจากภาษา Java ด้วย Apache NetBeans IDE โดยใช้ JavaFX และ Scene Builder ในการสร้าง GUI

![alt text](https://github.com/tsunafield1/UNO_card_game/blob/master/Example.png?raw=true)
![alt text](https://github.com/tsunafield1/UNO_card_game/blob/master/Example2.png?raw=true)

คลิปนำเสนอผลงาน: https://youtu.be/AKcmXWNQJeI

---------------------------

ใน Data มี 6 ไฟล์
- leaderboard.dat คือ ไฟล์ที่เก็บชื่อ และคะแนนใน leaderboard ไว้
- name.dat คือ ไฟล์ที่เก็บชื่อผู้เล่นในเกมรอบนั้นๆ ไว้
- nameOfBot.dat คือ ไฟล์ที่เก็บชื่อของบอทที่เป็นไปได้ทั้งหมดไว้
- numberOfPlayer.dat คือ ไฟล์ที่เก็บจำนวนผู้เล่นในเกมรอบนั้นๆ ไว้
- score.dat คือ ไฟล์ที่เก็บคะแนนปัจจุบันของผู้เล่นไว้
- winner.dat คือ ไฟล์ที่เก็บชื่อของผู้ชนะในเกมรอบนั้นๆ ไว้

ใน Image มี 2 โฟลเดอร์ และ 2 ไฟล์
- Card คือ โฟลเดอร์ที่เก็บรูปของการ์ดในเกมทั้งหมดไว้
- HowToPlay คือ โฟลเดอร์ที่เก็บรูปที่ใช้ในเมนู How To Play ไว้
- MenuBackground.jpg คือ รูปที่ใช้เป็นพื้นหลังของเมนูหลักในเกม
- MenuLogo คือ รูปโลโก้ UNO ที่ใช้ในเกม

ใน dist มี 1 โฟลเดอร์
- lib คือ ไลบรารี่ของ JavaFx ที่ใช้ในเกม

ใน src/Project มี .java 11 ไฟล์
- Card.java คือ class ที่ใช้สร้าง object ของการ์ดแต่ละใบ
- Deck.java คือ class ที่ใช้สร้าง object ของสำรับการ์ด ที่ใช้ในการจั่ว
- GameoverController.java คือ class ที่เป็นตัวควบคุมหน้าต่าง Game Over
- HowToPlayController.java คือ class ที่เป็นตัวควบคุมหน้าต่าง How To Play
- INGameController.java คือ class ที่เป็นตัวควบคุมหน้าต่างขณะที่กำลังเล่นเกม
- InputNameController.java คือ class ที่เป็นตัวควบคุมหน้าต่างใส่ชื่อผู้เล่น
- LeaderboardController.java คือ class ที่เป็นตัวควบคุมหน้าต่าง Leaderboard
- MenuController.java คือ class ที่เป็นตัวควบคุมหน้าต่างเมนูหลักของเกม
- Play.java คือ class ที่ใช้ในการเริ่มการทำงานของเกม
- SelectNumberController.java คือ class ที่เป็นตัวควบคุมหน้าต่างเลือกจำนวนผู้เล่นในเกม
- UNOGame.java คือ class ที่ใช้สร้าง object ของเกมแต่ละรอบ

มี .fxml 7 ไฟล์
- Gameover.fxml คือ GUI ของหน้าต่าง Game Over
- INGame.fxml คือ GUI ของหน้าต่างขณะที่กำลังเล่นเกม
- InputName.fxml คือ GUI ของหน้าต่างใส่ชื่อผู้เล่น
- Leaderboard.fxml คือ GUI ของหน้าต่าง Leaderboard
- Menu.fxml คือ GUI ของหน้าต่างเมนูหลักของเกม
- SelectNumber.fxml คือ GUI ของหน้าต่างเลือกจำนวนผู้เล่นในเกม
- howToPlay.fxml คือ GUI ของหน้าต่าง How To Play

โปรเจคนี้เป็นส่วนหนึ่งของวิชา Object Oriented Programming ภาควิชาวิศวกรรมคอมพิวเตอร์ คณะวิศวกรรมศาสตร์ สถาบันเทคโนโลยีพระจอมเกล้าเจ้าคุณทหารลาดกระบัง
