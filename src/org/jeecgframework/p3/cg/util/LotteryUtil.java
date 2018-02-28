package org.jeecgframework.p3.cg.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.jeecg.p3.bigwheel.entity.ActivityBigwheelAward;
 
/**
 * 不同概率抽奖工具包
 *
 * @author Shunli
 */
public class LotteryUtil {
    /**
     * 抽奖
     *
     * @param orignalRates
     *            原始的概率列表，保证顺序和实际物品对应
     * @return
     *         物品的索引
     */
    public static int lottery(List<ActivityBigwheelAward> orignalRates , Double noLuky , Double sumprob) {
        if (orignalRates == null || orignalRates.isEmpty()) {
            return -1;
        }
        // 根据区块值来获取抽取到的物品索引
        double nextDouble = Math.random();
        //如果区块值在不中奖的概率范围内
        if (nextDouble > noLuky) {
            return  -1;
        }
        int b = (int) (sumprob*100);
        double Doublenew = (double)new Random().nextInt(b)/100;
        ActivityBigwheelAward newa =new ActivityBigwheelAward();
        newa.setProb(Doublenew);
        orignalRates.add(newa);
        // 计算每个物品在总概率的基础下的概率情况

        Collections.sort(orignalRates,Collections.reverseOrder());
 
        return orignalRates.indexOf(newa);
    }
}