import java.io.PrintStream;
import java.util.Scanner;

public class QuestionTree{
    private int totalGames;
    private int gamesWon;
    private QuestionNode root;
    private UserInterface ui;



    public QuestionTree(UserInterface ui){
        this.ui = ui;

    }

    public void play(){
        root = getCurrentQuestion(root);
        totalGames += 1;
    }
    public QuestionNode getCurrentQuestion(QuestionNode q){
        if(q.isAnswer()){
            ui.print("Would your object happen to be " + q.question + "?");
            if(ui.nextBoolean()){
                ui.println("I win!");
                gamesWon += 1;
            }
            else{
                ui.print("I lose. What is your object?");
                q = generateNewQuestion(q);  
            }
        }
        else{
            ui.print(q.question);
            if(ui.nextBoolean()){
                q.yesAnswer = getCurrentQuestion(q.yesAnswer);
            }
            else{
                q.noAnswer = getCurrentQuestion(q.noAnswer);
            }
        }
        return q;
    }
    
    public QuestionNode generateNewQuestion(QuestionNode q){
        QuestionNode question = new QuestionNode(ui.nextLine());
        ui.print("Type a yes/no question to distinguish your item from " + q.question + ":");
        String userString = ui.nextLine();
        ui.print("And what is the answer for your object? ");
        if(ui.nextBoolean()){
            return new QuestionNode(userString, question, q);   
        }
        else{
            return new QuestionNode(userString, q, question);
        }
    }

    public void save(PrintStream output){
        save(output, root);
    }
    public void save(PrintStream output, QuestionNode curr){
        if(curr.isAnswer()){
            output.print("A:" + curr) ;

        }
        else{
            output.print("Q:" + curr);
            save(output, curr.yesAnswer);
            output.println();
            save(output,curr.noAnswer);
        }
    }
    public QuestionNode loadQuestionNode(Scanner input){
        QuestionNode curr = null;
        if(input.hasNext()){
            String[] line = input.nextLine().split(":", 2 );
            if(line[0].charAt(0)== 'A')
                curr = new QuestionNode(line[1]);
            else{
                curr = new QuestionNode(line[1], loadQuestionNode(input), loadQuestionNode(input));
            }
        }
        if(curr == null){
            return new QuestionNode("computer");
        }
        return curr;
    }


    public void load(Scanner input){
        root = loadQuestionNode(input);

    }

    public int totalGames(){
        return totalGames;
    }

    public int gamesWon(){
        return gamesWon;
    }
}