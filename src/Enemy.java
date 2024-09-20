import java.util.HashMap;
import java.util.Random;
import javax.swing.*;

/** clase enemigo **/
public class Enemy {
    private final String name;
    private final HashMap<Stats, Integer> stats;

    public Enemy(String name) {
        this.name = name;
        this.stats = new HashMap<>();
        initializeStats();
    }

    /** Inicializar estadisticas **/
    private void initializeStats() {
        stats.put(Stats.MAX_HP, 80);
        stats.put(Stats.HP, 80);
        stats.put(Stats.ATTACK, 15);
        stats.put(Stats.DEFENSE, 8);
        stats.put(Stats.SPEED, 4);
    }

    public String getName() {
        return name;
    }

    /** Define ataque **/
    public void attack(Player player) {
        int damage = calculateDamage(player);
        player.getStats().put(Stats.HP, Math.max(player.getStats().get(Stats.HP) - damage, 0));
        JOptionPane.showMessageDialog(null, name + " ataca a " + player.getName() + " e inflige " + damage + " de daño.");
    }

    /** Calcula el daño **/
    private int calculateDamage(Player player) {
        int attackPower = stats.get(Stats.ATTACK);
        int defensePower = player.getStats().get(Stats.DEFENSE);
        Random rand = new Random();
        return Math.max(attackPower - defensePower + rand.nextInt(5), 0);
    }

    /** Daño causado **/
    public void takeDamage(int damage) {
        stats.put(Stats.HP, Math.max(stats.get(Stats.HP) - damage, 0));
    }

    /** Mantener vivo **/
    public boolean isAlive() {
        return stats.get(Stats.HP) > 0;
    }

    public HashMap<Stats, Integer> getStats() {
        return stats;
    }
}
