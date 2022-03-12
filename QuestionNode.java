public class QuestionNode {
    public String question;
    public QuestionNode yesAnswer;
    public QuestionNode noAnswer;

    public QuestionNode(String q){
        question = q;
    }
    public QuestionNode(String q, String yes, String no){
        question = q;
        addAnswer(yes, true);
        addAnswer(no, false);
    }
    public QuestionNode(String q, QuestionNode yes, QuestionNode no){
        question = q;
        yesAnswer = yes;
        noAnswer = no;
    }
    public void addAnswer(String a, boolean yes){
        if(yes){
            yesAnswer = new QuestionNode(a);
        }
        else{
            noAnswer = new QuestionNode(a);
        }

    }
    public boolean isAnswer(){
        return yesAnswer == null;
    }
}


