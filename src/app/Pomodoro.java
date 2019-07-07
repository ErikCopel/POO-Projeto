package app;

public class Pomodoro {

    public static void main(String[] args) {
        
        Perfil joao = new Perfil("Joao");

        System.out.println(joao.getNome());

        joao.setNome("Maria");

        System.out.println(joao.getNome());

    }
}