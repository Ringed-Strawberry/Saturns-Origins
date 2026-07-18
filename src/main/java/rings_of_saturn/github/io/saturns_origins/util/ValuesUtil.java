package rings_of_saturn.github.io.saturns_origins.util;

public class ValuesUtil {

    //VOID GATEWAY
    public static int PORTAL_COOLDOWN_TICKS = 1600; // 80s
    public static int PORTAL_ENTITY_LIFETIME_TICKS = 800;         // 40s (PortalBlockEntity)
    public static int PORTAL_TELEPORT_COOLDOWN = 40;// 2s

    //BACKSTAB
    public static int INVULNERABLE_FRAMES_TICKS = 10; // 0.5s (The "parry" timer)


    //QUICK ESCAPE
    public static double DAMAGE_TELEPORT_HEALTH_THRESHOLD = 6.0;  // health to trigger teleport

    //MAGIC PLATFORM
    public static int PLATFORM_LIFETIME_TICKS = 100;              // 5s
    public static int PLATFORM_PARTICLE_GRID = 10;                // How many particles there are


    // SNEAKY BIRD
    public static int INVIS_COOLDOWN_TICKS = 100;                 // 5s
    public static double INVIS_DETECT_RANGE = 5.0;                // The range where it disables the invis if a player is nearby

    //BLOODLUST
    public static int BLOODLUST_DURATION_TICKS = 300;        // 15 seconds

    public static double BLOODLUST_INSTINCT_HORIZONTAL_RANGE = 32.0;        // X/Z radius
    public static double BLOODLUST_INSTINCT_VERTICAL_RANGE = 8.0;           // Y radius
    public static float BLOODLUST_INSTINCT_EXHAUSTION_PER_TICK = 0.1F;

    public static int BLOODLUST_INSTINCT_TIMEOUT_TICKS = 5;


    //SWARM
    public static double SWARM_SPEED = 1.5;
    public static double SWARM_RADIUS = 1.0;
    public static int FEATHER_DAMAGE = 2;

    //AUTOAIM
    public static double FEATHER_AUTO_AIM_RANGE = 8.0;            // Bigger Value = Better Aimbot (in theory)
    public static double PROJECTILE_AUTO_AIM_RANGE = 4.0;
}
