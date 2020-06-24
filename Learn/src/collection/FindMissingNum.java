package collection;

import java.util.ArrayList;

/**
 * @author shingo_ge
 * 给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字
 */
public class FindMissingNum {
    public static void main(String[] args) {
        //开始数
        int startNmu = 10;
        //结束数
        int endNmu = 20;
        ArrayList<Integer> list = new ArrayList<>();
        //将数添加到集合
        for (int i = startNmu; i <= endNmu; i++) {
            list.add(i);
        }
        System.out.println(list.toString());
        //产生一个的随机数
        int randomNmu = (int) (Math.random() * list.size());
        //从集合中随机删除一个数
        Integer removeNum = list.remove(randomNmu);
        System.out.println(removeNum);

        int missingNum = findMissingNumber(startNmu,endNmu,list);
        System.out.println(missingNum);
        System.out.println(missingNum==removeNum?"测试通过":"测试失败");
    }

    /**
     * @param startNmu 查找开始数
     * @param endNmu  查找结束数
     * @param list 查找集合
     * @return 集合中缺失的数
     * 寻找集合中缺失的数，实现方法，等差数列和-集合内数的和=缺失的数
     * */
    private static int findMissingNumber(int startNmu, int endNmu, ArrayList<Integer> list) {
        //集合内数的和
        int listSum = 0;
        for (Integer integer : list) {
            listSum = listSum + integer;
        }
        /*等差数列公式：   末项=首项+（项数-1）×公差
        *               项数=（末项－首项）/公差+1
        *               首项=末项－（项数-1）×公差
        *               和=（首项+末项）×项数/2
        * */
        int sequenceSum = (startNmu + endNmu) * (endNmu - startNmu + 1) / 2;
        return sequenceSum -listSum;
    }
}
