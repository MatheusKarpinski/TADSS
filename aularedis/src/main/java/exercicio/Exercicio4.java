package exercicio;

import redis.clients.jedis.Jedis;

public class Exercicio4 {

    public static void main(String[] args) {

        Jedis redis = new Jedis("localhost", 6379);

        redis.rpush("usuarios:list",
                "Matheus Karpinski",
                "Vitor Machado Blume",
                "Rhuan José Voltolini",
                "Gláucio",
                "Maria",
                "Joãozinho",
                "Julia",
                "Lucas",
                "Fernanda",
                "Ricardo"
        );

        System.out.println("10 usuários adicionados");

        redis.close();
    }
}