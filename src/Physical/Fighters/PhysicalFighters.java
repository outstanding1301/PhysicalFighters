/*     */ package Physical.Fighters;
/*     */ 
/*     */ import Physical.Fighters.MainModule.CommandManager;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MajorModule.AbilityList;
/*     */ import Physical.Fighters.Script.MainScripter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Timer;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class PhysicalFighters extends org.bukkit.plugin.java.JavaPlugin
/*     */ {
/*  18 */   public static int BuildNumber = 170802;
/*     */   
/*  20 */   public static boolean Invincibility = false;
/*  21 */   public static boolean DefaultArmed = true;
/*  22 */   public static boolean SRankUsed = true;
/*  23 */   public static boolean Respawn = false;
/*  24 */   public static boolean AutoKick = true;
/*  25 */   public static boolean AutoBan = true;
/*  26 */   public static boolean HalfMonsterDamage = false;
/*  27 */   public static boolean AutoDifficultySetting = true;
/*  28 */   public static boolean MaxLevelSurvival = false;
/*  29 */   public static int Setlev = 222;
/*  30 */   public static int EarlyInvincibleTime = 10;
/*  31 */   public static boolean NoFoodMode = true;
/*  32 */   public static boolean KillerOutput = true;
/*  33 */   public static boolean AutoCoordinateOutput = true;
/*  34 */   public static boolean NoAnimal = false;
/*  35 */   public static boolean NoAbilitySetting = false;
/*  36 */   public static boolean NoClearInventory = false;
/*  37 */   public static boolean PrintTip = true;
/*  38 */   public static boolean ReverseMode = false;
/*  39 */   public static boolean AutoSave = false;
/*  40 */   public static boolean InventorySave = false;
/*  41 */   public static boolean AbilityOverLap = false;
/*  42 */   public static boolean InfinityDur = true;
/*  43 */   public static int RestrictionTime = 15;
/*  44 */   public static boolean Kimiedition = false;
/*  45 */   public static boolean Specialability = false;
/*  46 */   public static boolean WoodGive = false;
/*  47 */   public static boolean TableGive = false;
/*  48 */   public static boolean Gods = false;
/*  49 */   public static boolean Toner = false;
/*  50 */   public static boolean AllowND = true;
/*  51 */   public static boolean LOL = false;
/*  52 */   public static boolean canstart = true;
/*  53 */   public static boolean easteregg = false;
/*  54 */   public static boolean change = false;
/*     */   
/*  56 */   public static Logger log = Logger.getLogger("Minecraft");
/*     */   
/*     */   public static Timer TracerTimer;
/*     */   
/*     */   public CommandManager cm;
/*     */   public MainScripter scripter;
/*     */   public AbilityList A_List;
/*     */   
/*     */   public void onEnable()
/*     */   {
/*  67 */     Physical.Fighters.MinerModule.ACC.DefaultItem = 
/*  68 */       Material.IRON_INGOT.getId();
/*     */     
/*  70 */     log.info(String.format("(!)빌드정보 " + String.valueOf(BuildNumber), 
/*  71 */       new Object[0]));
/*  72 */     log.info(String.format("(!)Edit By 염료", new Object[0]));
/*  73 */     this.cm = new CommandManager(this);
/*     */     
/*  75 */     getServer().getPluginManager().registerEvents(new EventManager(), this);
/*     */     
/*  77 */     log.info(String.format("(!)기본설정 로드중입니다.", new Object[0]));
/*  78 */     getConfig().options().copyDefaults(true);
/*  79 */     saveConfig();
/*     */     
/*  81 */     Invincibility = getConfig().getBoolean("시작후 초반 무적");
/*  82 */     DefaultArmed = getConfig().getBoolean("기본 무장 제공");
/*  83 */     Respawn = getConfig().getBoolean("시작시 리스폰으로 이동");
/*  84 */     AutoKick = getConfig().getBoolean("사망시 자동으로 킥");
/*  85 */     AutoBan = getConfig().getBoolean("사망시 자동으로 밴(킥이 활성화 되어야 가능)");
/*  86 */     SRankUsed = getConfig().getBoolean("S랭크 능력 사용");
/*  87 */     HalfMonsterDamage = getConfig().getBoolean("몬스터의 공격력 반감");
/*  88 */     AutoDifficultySetting = getConfig().getBoolean("난이도 자동으로 Easy로 설정");
/*  89 */     MaxLevelSurvival = getConfig().getBoolean("레벨 지급");
/*  90 */     Setlev = getConfig().getInt("레벨 설정");
/*  91 */     EarlyInvincibleTime = getConfig().getInt("초반 무적 시간(분 단위)");
/*  92 */     NoFoodMode = getConfig().getBoolean("배고픔 무한 모드(관련 능력은 알아서 상향됨)");
/*  93 */     KillerOutput = getConfig().getBoolean("죽을 경우 죽인 사람을 보여줌");
/*  94 */     AutoCoordinateOutput = getConfig().getBoolean("일정시간마다 좌표 표시");
/*  95 */     NoAnimal = getConfig().getBoolean("동물 비활성화");
/*  96 */     NoAbilitySetting = getConfig().getBoolean("시작시 능력 추첨 안함");
/*  97 */     NoClearInventory = getConfig().getBoolean("시작시 인벤토리 초기화 안함");
/*  98 */     PrintTip = getConfig().getBoolean("시작후 팁 출력함");
/*  99 */     AutoSave = getConfig().getBoolean("서버 오토 세이브");
/* 100 */     InventorySave = getConfig().getBoolean("인벤토리 세이브");
/* 101 */     AbilityOverLap = getConfig().getBoolean("능력 중복 가능");
/* 102 */     InfinityDur = getConfig().getBoolean("내구도 무한");
/* 103 */     RestrictionTime = getConfig().getInt("일부 능력 금지 시간(분 단위, 0은 사용 안함)");
/* 104 */     Kimiedition = getConfig().getBoolean("극한모드");
/* 105 */     Specialability = getConfig().getBoolean("안좋은능력제거");
/* 106 */     TableGive = getConfig().getBoolean("책장 지급");
/* 107 */     WoodGive = getConfig().getBoolean("나무 지급");
/* 108 */     Gods = getConfig().getBoolean("신등급활성화");
/* 109 */     Toner = getConfig().getBoolean("지형파괴능력제거");
/* 110 */     AllowND = getConfig().getBoolean("자연사허용");
/* 111 */     LOL = getConfig().getBoolean("팀전");
/* 112 */     change = getConfig().getBoolean("10분마다능력변경");
/*     */     
/* 114 */     log.info(String.format("(!)능력을 초기화합니다.", new Object[0]));
/* 115 */     Physical.Fighters.MainModule.AbilityBase.InitAbilityBase(this, this.cm);
/* 116 */     this.A_List = new AbilityList();
/*     */     
/* 118 */     log.info(String.format("(!)스크립터를 초기화합니다.", new Object[0]));
/* 119 */     this.scripter = new MainScripter(this, this.cm);
/* 120 */     if ((Invincibility) && (EarlyInvincibleTime <= 0)) {
/* 121 */       log.info(String.format("(!)초반무적이 1분으로 설정됩니다. [E.시간이 0분 이하입니다]", 
/* 122 */         new Object[0]));
/* 123 */       EarlyInvincibleTime = 1;
/*     */     }
/*     */     
/* 126 */     if (RestrictionTime < 0) {
/* 127 */       log.info(String.format("(!)제약 시간 값은 0보다 커야합니다. 0으로 설정됩니다.", 
/* 128 */         new Object[0]));
/* 129 */       RestrictionTime = 0;
/*     */     }
/*     */     
/* 132 */     log.info(String.format("(!)능력 %d개가 등록되있습니다.", new Object[] {
/* 133 */       Integer.valueOf(AbilityList.AbilityList.size() - 1) }));
/* 134 */     if (Kimiedition) {
/* 135 */       log.info(String.format("(!)극한모드 적용", new Object[0]));
/*     */     }
/* 137 */     if (Specialability) {
/* 138 */       log.info(String.format("(!)안좋은 능력을 제거합니다.", new Object[0]));
/*     */     }
/* 140 */     if (Gods) {
/* 141 */       log.info(String.format("(!)'신' 등급 활성화!", new Object[0]));
/*     */     }
/* 143 */     if (AutoSave)
/* 144 */       for (World w : org.bukkit.Bukkit.getServer().getWorlds())
/* 145 */         w.setAutoSave(true);
/*     */   }
/*     */   
/*     */   public void onDisable() {
/* 149 */     TracerTimer.cancel();
/* 151 */     log.info(String.format("(!)플러그인을 종료합니다.", new Object[0]));
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\PhysicalFighters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */