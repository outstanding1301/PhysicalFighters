/*     */ package Physical.Fighters.MainModule;
/*     */ 
/*     */ import Physical.Fighters.MajorModule.AbilityList;
/*     */ import Physical.Fighters.MajorModule.CoolDownTimer;
/*     */ import Physical.Fighters.MajorModule.DurationTimer;
/*     */ import Physical.Fighters.MajorModule.RestrictionTimer;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public abstract class AbilityBase
/*     */ {
/*     */   protected static CommandManager cm;
/*     */   protected static PhysicalFighters va;
/*  32 */   public static int AbilityCount = 0;
/*     */   
/*  34 */   public static final RestrictionTimer restrictionTimer = new RestrictionTimer();
/*     */   private Rank rank;
/*     */   private Player player;
/*     */   private String AbilityName;
/*     */   private Type type;
/*     */   private String[] Guide;
/*  40 */   private int CoolDown = 0;
/*  41 */   private int Duration = 0;
/*     */   private CoolDownTimer CTimer;
/*     */   private DurationTimer DTimer;
/*  44 */   private boolean RunAbility = true;
/*  45 */   private ShowText showtext = ShowText.All_Text;
/*     */   
/*     */ 
/*     */   public abstract int A_Condition(Event paramEvent, int paramInt);
/*     */   
/*     */ 
/*     */   public abstract void A_Effect(Event paramEvent, int paramInt);
/*     */   
/*     */ 
/*     */   public void A_CoolDownStart() {}
/*     */   
/*     */ 
/*     */   public void A_CoolDownEnd() {}
/*     */   
/*     */ 
/*     */   public void A_DurationStart() {}
/*     */   
/*     */ 
/*     */   public void A_DurationEnd() {}
/*     */   
/*     */   public void A_FinalDurationEnd() {}
/*     */   
/*     */   public void A_SetEvent(Player p) {}
/*     */   
/*     */   public void A_ResetEvent(Player p) {}
/*     */   
/*     */   public final void RegisterLeftClickEvent()
/*     */   {
/*  73 */     EventManager.LeftHandEvent.add(this);
/*     */   }
/*     */   
/*     */   public final void RegisterRightClickEvent() {
/*  77 */     EventManager.RightHandEvent.add(this);
/*     */   }
/*     */   
/*     */   public final Player GetPlayer() {
/*  81 */     return this.player;
/*     */   }
/*     */   
/*     */   public final boolean hasPlayer() {
/*  85 */     if (this.player == null)
/*  86 */       return false;
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public final void SetPlayer(Player p, boolean textout) {
/*  91 */     this.DTimer.StopTimer();
/*  92 */     this.CTimer.StopTimer();
/*  93 */     if (this.player != null) {
/*  94 */       if (textout) {
/*  95 */         this.player.sendMessage(String.format(ChatColor.RED + "%s" + 
/*  96 */           ChatColor.WHITE + " 능력이 해제되었습니다.", 
/*  97 */           new Object[] { GetAbilityName() }));
/*     */       }
/*  99 */       A_ResetEvent(this.player);
/*     */     }
/* 101 */     if ((p != null) && (this.RunAbility)) {
/* 102 */       if (textout) {
/* 103 */         p.sendMessage(String.format(ChatColor.GREEN + "%s" + 
/* 104 */           ChatColor.WHITE + " 능력이 설정되었습니다.", 
/* 105 */           new Object[] { GetAbilityName() }));
/*     */       }
/* 107 */       A_SetEvent(p);
/*     */     }
/* 109 */     this.player = p;
/*     */   }
/*     */   
/*     */   public final String GetAbilityName() {
/* 113 */     return this.AbilityName;
/*     */   }
/*     */   
/*     */   public final Type GetAbilityType() {
/* 117 */     return this.type == null ? null : this.type;
/*     */   }
/*     */   
/*     */ 
/* 121 */   public final String[] GetGuide() { return this.Guide; }
/*     */   
/*     */   public final LinkedList<String> GetGuide2() {
/* 124 */     LinkedList<String> s = new LinkedList();
/* 125 */     String[] arrayOfString; int j = (arrayOfString = this.Guide).length; for (int i = 0; i < j; i++) { String z = arrayOfString[i];
/* 126 */       s.add(z);
/*     */     }
/* 128 */     return s;
/*     */   }
/*     */   
/*     */   public final int GetCoolDown() {
/* 132 */     return this.CoolDown;
/*     */   }
/*     */   
/*     */   public final Rank GetRank() {
/* 136 */     return this.rank;
/*     */   }
/*     */   
/*     */   public final int GetDuration() {
/* 140 */     return this.Duration;
/*     */   }
/*     */   
/*     */   public final boolean GetDurationState() {
/* 144 */     return this.DTimer.GetTimerRunning();
/*     */   }
/*     */   
/*     */   public final void SetRunAbility(boolean RunAbility) {
/* 148 */     this.RunAbility = RunAbility;
/*     */   }
/*     */   
/*     */   public final boolean GetRunAbility() {
/* 152 */     return this.RunAbility;
/*     */   }
/*     */   
/*     */   public final ShowText GetShowText() {
/* 156 */     return this.showtext;
/*     */   }
/*     */   
/*     */   public final void AbilityDTimerCancel() {
/* 160 */     this.DTimer.StopTimer();
/*     */   }
/*     */   
/*     */   public final void AbilityCTimerCancel() {
/* 164 */     this.CTimer.StopTimer();
/*     */   }
/*     */   
/*     */   public final boolean PlayerCheck(Player p) {
/* 168 */     if ((this.player != null) && 
/* 169 */       (p.getName().equalsIgnoreCase(this.player.getName())) && (
/* 170 */       (this.player.isDead()) || 
/* 171 */       (Bukkit.getServer().getPlayerExact(this.player.getName()) != null))) {
/* 172 */       this.player = p;
/* 173 */       return true;
/*     */     }
/*     */     
/* 176 */     return false;
/*     */   }
/*     */   
/*     */   public final boolean PlayerCheck(Entity e) {
/* 180 */     return ((e instanceof Player)) && ((Player)e != null) && 
/* 181 */       (PlayerCheck((Player)e));
/*     */   }
/*     */   
/*     */   public final boolean ItemCheck(int itemID)
/*     */   {
/* 186 */     return GetPlayer().getItemInHand().getType().getId() == itemID;
/*     */   }
/*     */   
/*     */   public final boolean AbilityExcute(Event event) {
/* 190 */     return AbilityExcute(event, 0);
/*     */   }
/*     */   
/*     */   public final boolean AbilityExcute(Event event, int CustomData) {
/* 194 */     if ((this.player != null) && (this.RunAbility)) {
/* 195 */       int cd = A_Condition(event, CustomData);
/* 196 */       if (cd == -2) {
/* 197 */         return true;
/*     */       }
/* 199 */       if (cd != -1) {
/* 200 */         if ((this.type == Type.Active_Continue) || 
/* 201 */           (this.type == Type.Active_Immediately)) {
/* 202 */           if (this.DTimer.GetTimerRunning())
/*     */           {
/* 204 */             GetPlayer().sendMessage(
/* 205 */               String.format(ChatColor.WHITE + "%d초" + 
/* 206 */               ChatColor.GREEN + 
/* 207 */               " 후 지속시간이 끝납니다.", 
/* 208 */               new Object[] {
/* 209 */               Integer.valueOf(this.DTimer
/* 210 */               .GetCount()) }));
/* 211 */             return true;
/*     */           }
/* 213 */           if (this.CTimer.GetTimerRunning()) {
/* 214 */             if (GetShowText() != ShowText.No_CoolDownText) {
/* 215 */               GetPlayer().sendMessage(
/* 216 */                 String.format(ChatColor.WHITE + "%d초" + 
/* 217 */                 ChatColor.RED + 
/* 218 */                 " 후 능력을 다시 사용하실 수 있습니다.", 
/* 219 */                 new Object[] {
/* 220 */                 Integer.valueOf(this.CTimer
/* 221 */                 .GetCount()) }));
/*     */             }
/* 223 */             return true;
/*     */           }
/*     */           
/* 226 */           if (GetShowText() != ShowText.Custom_Text)
/* 227 */             GetPlayer().sendMessage(ACC.DefaultAbilityUsed);
/*     */         }
/* 229 */         if (this.type == Type.Active_Immediately) {
/* 230 */           A_Effect(event, cd);
/* 231 */           if (GetCoolDown() != 0)
/* 232 */             this.CTimer.StartTimer(GetCoolDown(), true);
/* 233 */         } else if (this.type == Type.Active_Continue) {
/* 234 */           this.DTimer.StartTimer(GetDuration(), true);
/*     */         } else {
/* 236 */           A_Effect(event, cd);
/*     */         }
/* 238 */         return true;
/*     */       }
/*     */     }
/* 241 */     return false;
/*     */   }
/*     */   
/*     */   public final int goPlayerVelocity(Player p1, Player p2, int value) {
/* 245 */     p1.setVelocity(p1.getVelocity().add(
/* 246 */       p2.getLocation().toVector()
/* 247 */       .subtract(p1.getLocation().toVector()).normalize()
/* 248 */       .multiply(value)));
/* 249 */     return 0;
/*     */   }
/*     */   
/*     */   public static final int goVelocity(Player p1, Location lo, int value) {
/* 253 */     p1.setVelocity(p1.getVelocity().add(
/* 254 */       lo.toVector().subtract(p1.getLocation().toVector()).normalize()
/* 255 */       .multiply(value)));
/* 256 */     return 0;
/*     */   }
/*     */   
/*     */   public static final int ArrowVelocity(Arrow a, Location lo, int value) {
/* 260 */     a.setVelocity(a.getVelocity().add(
/* 261 */       lo.toVector().subtract(a.getLocation().toVector()).normalize()
/* 262 */       .multiply(value)));
/* 263 */     return 0;
/*     */   }
/*     */   
/*     */   public static final int Direction(Player p) {
/* 267 */     Location loc = p.getLocation();
/* 268 */     Location loc2 = p.getTargetBlock(null, 0).getLocation();
/* 269 */     int x = (int)Math.abs(Math.abs(loc.getX()) - Math.abs(loc2.getX()));
/* 270 */     int z = (int)Math.abs(Math.abs(loc.getZ()) - Math.abs(loc2.getZ()));
/* 271 */     if (loc == loc2) {
/* 272 */       return 10;
/*     */     }
/* 274 */     if (x > z) {
/* 275 */       if (loc.getX() > loc2.getX()) {
/* 276 */         return 1;
/*     */       }
/*     */       
/* 279 */       return 3;
/*     */     }
/*     */     
/* 282 */     if (loc.getZ() > loc2.getZ()) {
/* 283 */       return 2;
/*     */     }
/*     */     
/* 286 */     return 4;
/*     */   }
/*     */   
/*     */   public static final int LookAngle(Location l, Location l2, int value) {
/* 290 */     double degrees = Math.toRadians(-(l.getYaw() % 360.0F));
/* 291 */     double ydeg = Math.toRadians(-(l.getPitch() % 360.0F));
/* 292 */     l2.setX(l.getX() + 2 * value + 1.0D * (Math.sin(degrees) * Math.cos(ydeg)));
/* 293 */     l2.setY(l.getY() + 2 * value + 1.0D * Math.sin(ydeg));
/* 294 */     l2.setZ(l.getZ() + 2 * value + 1.0D * (Math.cos(degrees) * Math.cos(ydeg)));
/* 295 */     return 0;
/*     */   }
/*     */   
/*     */   public final boolean AbilityDuratinEffect(Event event) {
/* 299 */     return AbilityDuratinEffect(event, 0);
/*     */   }
/*     */   
/*     */   public final boolean AbilityDuratinEffect(Event event, int CustomData) {
/* 303 */     if ((this.player != null) && (this.DTimer.GetTimerRunning())) {
/* 304 */       A_Effect(event, CustomData);
/* 305 */       return true;
/*     */     }
/* 307 */     return false;
/*     */   }
/*     */   
/*     */   protected final void InitAbility(String AbilityName, Type type, Rank rank, String... Manual)
/*     */   {
/* 312 */     this.AbilityName = AbilityName;
/* 313 */     this.type = type;
/* 314 */     this.Guide = new String[Manual.length];
/* 315 */     for (int loop = 0; loop < Manual.length; loop++)
/* 316 */       this.Guide[loop] = Manual[loop];
/* 317 */     this.CTimer = new CoolDownTimer(this);
/* 318 */     this.DTimer = new DurationTimer(this, this.CTimer);
/* 319 */     this.rank = rank;
/*     */   }
/*     */   
/*     */   protected final void InitAbility(int CoolDown, int Duration, boolean RunAbility)
/*     */   {
/* 324 */     InitAbility(CoolDown, Duration, RunAbility, ShowText.All_Text);
/*     */   }
/*     */   
/*     */   protected final void InitAbility(int CoolDown, int Duration, boolean RunAbility, ShowText showtext)
/*     */   {
/* 329 */     this.CoolDown = ((this.type == Type.Active_Continue) || 
/* 330 */       (this.type == Type.Active_Immediately) ? CoolDown : -1);
/* 331 */     this.Duration = (this.type == Type.Active_Continue ? Duration : -1);
/* 332 */     this.RunAbility = RunAbility;
/* 333 */     this.showtext = showtext;
/* 334 */     AbilityList.AbilityList.add(this);
/* 335 */     AbilityCount += 1;
/*     */   }
/*     */   
/*     */   public static final int GetAbilityCount() {
/* 339 */     return AbilityCount;
/*     */   }
/*     */   
/*     */   public static final AbilityBase FindAbility(Player p) {
/* 343 */     for (AbilityBase a : AbilityList.AbilityList)
/* 344 */       if (a.PlayerCheck(p))
/* 345 */         return a;
/* 346 */     return null;
/*     */   }
/*     */   
/*     */   public static final AbilityBase FindAbility(String name) {
/* 350 */     for (AbilityBase a : AbilityList.AbilityList)
/* 351 */       if (a.GetAbilityName().equalsIgnoreCase(name))
/* 352 */         return a;
/* 353 */     return null;
/*     */   }
/*     */   
/*     */   public static final void InitAbilityBase(PhysicalFighters va, CommandManager cm)
/*     */   {
/* 358 */     AbilityBase.va = va;
/* 359 */     AbilityBase.cm = cm;
/*     */   }
/*     */   
/*     */   public static enum Rank {
/* 363 */     SSS(ChatColor.GOLD + "Special Rank"),  SS(ChatColor.GRAY + "SS Rank"),  S(
/* 364 */       ChatColor.RED + "S Rank"),  A(ChatColor.GREEN + "A Rank"),  B(
/* 365 */       ChatColor.BLUE + "B Rank"),  C(ChatColor.YELLOW + "C Rank"),  F(
/* 366 */       ChatColor.BLACK + "F Rank"),  FF(ChatColor.BLACK + "개가 싸놓은 똥"),  GOD(
/* 367 */       ChatColor.WHITE + "신");
/*     */     
/*     */     private String s;
/*     */     
/*     */     private Rank(String s) {
/* 372 */       this.s = s;
/*     */     }
/*     */     
/*     */ 
/* 376 */     public String GetText() { return this.s; }
/*     */   }
/*     */   
/*     */   public final boolean isSword(ItemStack is) {
/* 380 */     if ((is.getType() == Material.WOOD_SWORD) || (is.getType() == Material.STONE_SWORD) || (is.getType() == Material.GOLD_SWORD) || (is.getType() == Material.IRON_SWORD) || (is.getType() == Material.DIAMOND_SWORD)) {
/* 381 */       return true;
/*     */     }
/* 383 */     return false;
/*     */   }
/*     */   
/* 386 */   public void ExplosionDMG(Player p, int distance, int dmg) { Player[] pl = Bukkit.getOnlinePlayers();
/* 387 */     for (Player t : pl) {
/* 388 */       if ((t != p) && (p.getLocation().distance(t.getLocation()) <= distance))
/* 389 */         t.damage(dmg, p);
/*     */     }
/*     */   }
/*     */   
/*     */   public void ExplosionDMG(Player p, Location l, int distance, int dmg) {
/* 394 */     Player[] pl = Bukkit.getOnlinePlayers();
/* 395 */     for (Player t : pl) {
/* 396 */       if ((t != p) && (l.distance(t.getLocation()) <= distance))
/* 397 */         t.damage(dmg, p);
/*     */     }
/*     */   }
/*     */   
/*     */   public void ExplosionDMGL(Player p, Location l, int distance, int dmg) {
/* 402 */     Player[] pl = Bukkit.getOnlinePlayers();
/* 403 */     for (Player t : pl)
/* 404 */       if ((t != p) && (l.distance(t.getLocation()) <= distance)) {
/* 405 */         t.damage(dmg, p);
/* 406 */         t.getWorld().strikeLightning(t.getLocation());
/*     */       }
/*     */   }
/*     */   
/*     */   public void ExplosionDMGPotion(Player p, Location l, int distance, int dmg, PotionEffectType pet, int dura, int amp) {
/* 411 */     Player[] pl = Bukkit.getOnlinePlayers();
/* 412 */     for (Player t : pl)
/* 413 */       if ((t != p) && (l.distance(t.getLocation()) <= distance)) {
/* 414 */         t.damage(dmg, p);
/* 415 */         t.addPotionEffect(new PotionEffect(pet, dura, amp));
/*     */       }
/*     */   }
/*     */   
/*     */   public void ExplosionDMG(Location l, int distance, int dmg) {
/* 420 */     Player[] pl = Bukkit.getOnlinePlayers();
/* 421 */     for (Player t : pl) {
/* 422 */       if (t.getLocation().distance(l) <= distance)
/* 423 */         t.damage(dmg);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setCool(int i) {
/* 428 */     this.CTimer.SetCount(i);
/*     */   }
/*     */   
/*     */ 
/* 432 */   public int getCool() { return this.CTimer.GetCount(); }
/*     */   
/*     */   public static enum ShowText {
/* 435 */     All_Text,  No_CoolDownText,  No_DurationText,  No_Text,  Custom_Text;
/*     */   }
/*     */   
/*     */   public static enum Type {
/* 439 */     Passive_AutoMatic,  Passive_Manual,  Active_Immediately,  Active_Continue;
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\MainModule\AbilityBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */