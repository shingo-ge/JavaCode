package collection;

import java.util.Comparator;

class UserComparator implements Comparator<Client> {
    public static final char VIP_SYMBOL = 'V';
    @Override
    public int compare(Client o1, Client o2) {
        if (o1.getLevel().charAt(0) == o2.getLevel().charAt(0)){
            int i1 = Integer.parseInt(o1.getLevel().substring(1));
            int i2 = Integer.parseInt(o2.getLevel().substring(1));
            return Integer.compare(i1, i2);
        }
        if (o1.getLevel().charAt(0) == VIP_SYMBOL){
            return -1;
        }else {
            return 1;
        }
    }
}
