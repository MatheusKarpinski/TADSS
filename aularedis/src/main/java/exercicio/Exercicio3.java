package exercicio;

import redis.clients.jedis.RedisClient;

public class Exercicio3 {

    public static void main(String[] args) {
        RedisClient redis = RedisClient.create("redis://localhost:6379");
        System.out.println(redis.ping());
        //System.out.println(redis.echo("Olá mundo"));
        System.out.println(redis.incr("programa:execucoes"));
        redis.close();
    }
}