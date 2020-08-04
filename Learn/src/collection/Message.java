package collection;

class Message {
    private int sequence;
    private String text;

    public Message(int sequence, String text) {
        this.sequence = sequence;
        this.text = text;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
