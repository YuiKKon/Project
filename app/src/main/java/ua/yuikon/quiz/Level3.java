package ua.yuikon.quiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class Level3 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Array array = new Array(); //новый об из класса
    Random random = new Random(); // генерация случайных чисел

    public int count = 0; //счетчик правильных ответов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.textView);
        text_levels.setText(R.string.level_3);


        final ImageView img_left = findViewById(R.id.img_left);
        final ImageView img_right = findViewById(R.id.img_right);
        //сругляет углы
        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);
        //пути к textView
        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);


        //развернуть на всеь экран
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //фон диалог окна
        ImageView background = findViewById(R.id.background);
        background.setImageResource(R.drawable.level3);

        //диалоговое окно в начале
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        //картинка в диалоговое окно
        ImageView previewimg = dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.preview_img_3);

        //фон диалог окна
        LinearLayout dialogfon = dialog.findViewById(R.id.dialogfor);
        dialogfon.setBackgroundResource(R.drawable.preview_background_3);

        //описание задания
        TextView textdescription = dialog.findViewById(R.id.texxtdescription);
        textdescription.setText(R.string.level3);

        //кнопка закрыть диалог окно
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {}
            dialog.dismiss();
            }
        });

        //кнопка продолжить
        Button btncontinue = dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        //_________________________________________
        //диалоговое окно в конце
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);

        //фон диалог окна
        LinearLayout dialogfonEnd = dialogEnd.findViewById(R.id.dialogfor);
        dialogfonEnd.setBackgroundResource(R.drawable.preview_background_3);

        //интересный факт
        TextView textdiscriptionEnd = dialogEnd.findViewById(R.id.texxtdescriptionEnd);
        textdiscriptionEnd.setText(R.string.level3_end);

        //кнопка закрыть диалог окно
        TextView btnclose2 = dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {}
                dialogEnd.dismiss();
            }
        });

        //кнопка продолжить
        Button btncontinue2 = dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, Level4.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e) {}

                dialogEnd.dismiss();
            }
        });
        //_________________________________________

        //назад в начало
        Button btn_back = findViewById(R.id.button_back);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {}
            }
        });

        //массив для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };

        //анимация
        final Animation a = AnimationUtils.loadAnimation(Level3.this, R.anim.alpha);

        numLeft = random.nextInt(21); //случайное число
        img_left.setImageResource(array.images3[numLeft]); // достаем из массива картинку
        text_left.setText(array.texts3[numLeft]); // достаем из массива текст

        numRight = random.nextInt(21);

        //цикл проверяет равенство чисел
        while (numLeft == numRight) {
            numRight = random.nextInt(21);
        }

        img_right.setImageResource(array.images3[numRight]);
        text_right.setText(array.texts3[numRight]);

        //обрабатываем нажатие на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //коснулись пальцем
                    img_right.setEnabled(false);
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //условия для левой картинки правильный ответ
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //отпустили палец
                    if (numLeft > numRight) {
                        //если левая больше
                        if (count < 20) {
                            count = count + 1;
                        }
                        //закрасить прогресс серым цветов
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы зеленым цветом
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //условие для левой картинки не правильный ответ
                    } else {
                        //если левая меньше
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        //закрасить прогресс серым цветов
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы зеленым цветом
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if (count == 20) {
                        //выход из уровня
                        dialogEnd.show();

                    } else {
                        numLeft = random.nextInt(21); //случайное число
                        img_left.setImageResource(array.images3[numLeft]); // достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]); // достаем из массива текст

                        numRight = random.nextInt(21);

                        //цикл проверяет равенство чисел
                        while (numLeft == numRight) {
                            numRight = random.nextInt(21);
                        }

                        img_right.setImageResource(array.images3[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]);

                        img_right.setEnabled(true); //включаем правую картинку
                    }
                }
                return true;
            }
        });


        //обрабатываем нажатие на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //коснулись пальцем
                    img_left.setEnabled(false); //блок левую
                    if (numLeft < numRight) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //условия для левой картинки правильный ответ
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //отпустили палец
                    if (numLeft < numRight) {
                        //если правая больше
                        if (count < 20) {
                            count = count + 1;
                        }
                        //закрасить прогресс серым цветов
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы зеленым цветом
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //условие для правой картинки не правильный ответ
                    } else {
                        //если правая меньше
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        //закрасить прогресс серым цветов
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы зеленым цветом
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if (count == 20) {
                        //выход из уровня
                        dialogEnd.show();

                    } else {
                        numLeft = random.nextInt(21); //случайное число
                        img_left.setImageResource(array.images3[numLeft]); // достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]); // достаем из массива текст

                        numRight = random.nextInt(21);

                        //цикл проверяет равенство чисел
                        while (numLeft == numRight) {
                            numRight = random.nextInt(21);
                        }

                        img_right.setImageResource(array.images3[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]);

                        img_left.setEnabled(true); //включаем левую картинку
                    }
                }
                return true;
            }
        });

    }

    //системная кнопка Назад
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level3.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {}
    }
}

