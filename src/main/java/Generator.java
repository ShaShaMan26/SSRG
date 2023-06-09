public class Generator {
    public static void main(String[] args) {
        int id = 0;
        int timeStamp = 1;
        int length = 312;

        StringBuilder str = new StringBuilder();

        while(timeStamp <= length) {
            System.out.printf("""
                    {
                        "id": %d,
                        "timeStamp": %d
                    },
                    """, id, timeStamp);
            timeStamp++;
            id++;
            if (id > 3) {
                id = 0;
            }
        }
    }
}
