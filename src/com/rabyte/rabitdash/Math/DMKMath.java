package com.rabyte.rabitdash.Math;

//TODO java�Դ���Math���ھ�ȷ
//�Զ�����Է��Ӹ�������
public class DMKMath {
    //degree to rad
    public static double toRadian(double degree) {
        // 1 degree = 0.0174rad
        //��ʵ���־��Ⱦ��Ѿ�����
        //��Ϊ��Ч���ֶ�һλ����������10��
        return degree * 0.0174;
    }

    public static double sign(double num) {
        if (num > 0) {
            return 1;
        } else if (num < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
