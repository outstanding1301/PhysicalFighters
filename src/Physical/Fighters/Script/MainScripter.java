/*     */ package Physical.Fighters.Script;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.CommandManager;
/*     */ import Physical.Fighters.MajorModule.AbilityList;
/*     */ import Physical.Fighters.MinerModule.AUC;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class MainScripter implements Physical.Fighters.MinerModule.CommandInterface
/*     */ {
/*  28 */   public static ScriptStatus Scenario = ScriptStatus.NoPlay;
/*  29 */   public LinkedList<Player> ExceptionList = new LinkedList();
/*  30 */   public static ArrayList<Player> PlayerList = new ArrayList();
/*  31 */   public ArrayList<Player> OKSign = new ArrayList();
/*     */   public PhysicalFighters va;
/*     */   public org.bukkit.World gameworld;
/*     */   public S_GameReady s_GameReady;
/*     */   public S_GameStart s_GameStart;
/*     */   public S_GameProgress s_GameProgress;
/*     */   public S_GameWarnning s_GameWarnning;
/*     */   
/*     */   public MainScripter(PhysicalFighters va, CommandManager cm) {
/*  40 */     this.va = va;
/*  41 */     cm.RegisterCommand(this);
/*     */     
/*  43 */     this.s_GameReady = new S_GameReady(this);
/*  44 */     this.s_GameStart = new S_GameStart(this);
/*  45 */     this.s_GameProgress = new S_GameProgress(this);
/*  46 */     this.s_GameWarnning = new S_GameWarnning(this);
/*     */   }
/*     */   
/*     */   public boolean onCommandEvent(CommandSender sender, Command command, String label, String[] data)
/*     */   {
/*  51 */     if ((sender instanceof Player)) {
/*  52 */       Player p = (Player)sender;
/*  53 */       if (PhysicalFighters.canstart) {
/*  54 */         if (data[0].equalsIgnoreCase("help")) {
/*  55 */           AUC.InfoTextOut(p);
/*  56 */         } else if (data[0].equalsIgnoreCase("start")) {
/*  57 */           this.gameworld = p.getWorld();
/*  58 */           this.s_GameReady.GameReady(p);
/*  59 */         } else if (data[0].equalsIgnoreCase("ob")) {
/*  60 */           vaob(p);
/*  61 */         } else if (data[0].equalsIgnoreCase("yes")) {
/*  62 */           vayes(p);
/*  63 */         } else if (data[0].equalsIgnoreCase("no")) {
/*  64 */           vano(p);
/*  65 */         } else if (data[0].equalsIgnoreCase("stop")) {
/*  66 */           vastop(p);
/*  67 */         } else if (data[0].equalsIgnoreCase("alist")) {
/*  68 */           vaalist(p);
/*  69 */         } else if (data[0].equalsIgnoreCase("elist")) {
/*  70 */           vaelist(p);
/*  71 */         } else if (data[0].equalsIgnoreCase("tc")) {
/*  72 */           vatc(p);
/*  73 */         } else if (data[0].equalsIgnoreCase("kill")) {
/*  74 */           vakill(p, data);
/*  75 */         } else if (data[0].equalsIgnoreCase("debug")) {
/*  76 */           vadebug(p);
/*  77 */         } else if (data[0].equalsIgnoreCase("skip")) {
/*  78 */           vaskip(p);
/*  79 */         } else if (data[0].equalsIgnoreCase("maker")) {
/*  80 */           vamaker(p);
/*  81 */         } else if (data[0].equalsIgnoreCase("uti")) {
/*  82 */           vauti(p);
/*  83 */         } else if (data[0].equalsIgnoreCase("abi")) {
/*  84 */           vaabi(p, data);
/*  85 */         } else if (data[0].equalsIgnoreCase("book")) {
/*  86 */           vabook(p, data);
/*  87 */         } else if (data[0].equalsIgnoreCase("ablist")) {
/*  88 */           vaablist(p, data);
/*  89 */         } else if (data[0].equalsIgnoreCase("go")) {
/*  90 */           vago(p);
/*  91 */         } else if (data[0].equalsIgnoreCase("inv")) {
/*  92 */           vainv(p);
/*  93 */         } else if (data[0].equalsIgnoreCase("hung")) {
/*  94 */           vahungry(p);
/*  95 */         } else if (data[0].equalsIgnoreCase("dura")) {
/*  96 */           vadura(p);
/*  97 */         } else if (data[0].equalsIgnoreCase("염료")) {
/*     */           try {
/*  99 */             vaeasteregg(p);
/*     */           }
/*     */           catch (IOException e) {
/* 102 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       } else
/* 106 */         sender.sendMessage("플러그인이 개조되었습니다. 게임을 진행할 수 없습니다.");
/* 107 */       return true;
/*     */     }
/*     */     
/* 110 */     if (PhysicalFighters.canstart) {
/* 111 */       if (data[0].equalsIgnoreCase("help")) {
/* 112 */         sender.sendMessage("프롬프트에서는 사용할수 없는 명령입니다.");
/* 113 */       } else if (data[0].equalsIgnoreCase("start")) {
/* 114 */         sender.sendMessage("프롬프트에서는 사용할수 없는 명령입니다.");
/* 115 */       } else if (data[0].equalsIgnoreCase("ob")) {
/* 116 */         sender.sendMessage("프롬프트에서는 사용할수 없는 명령입니다.");
/* 117 */       } else if (data[0].equalsIgnoreCase("yes")) {
/* 118 */         sender.sendMessage("프롬프트에서는 사용할수 없는 명령입니다.");
/* 119 */       } else if (data[0].equalsIgnoreCase("no")) {
/* 120 */         sender.sendMessage("프롬프트에서는 사용할수 없는 명령입니다.");
/* 121 */       } else if (data[0].equalsIgnoreCase("stop")) {
/* 122 */         vastop(sender);
/* 123 */       } else if (data[0].equalsIgnoreCase("alist")) {
/* 124 */         vaalist(sender);
/* 125 */       } else if (data[0].equalsIgnoreCase("elist")) {
/* 126 */         vaelist(sender);
/* 127 */       } else if (data[0].equalsIgnoreCase("tc")) {
/* 128 */         vatc(sender);
/* 129 */       } else if (data[0].equalsIgnoreCase("kill")) {
/* 130 */         vakill(sender, data);
/* 131 */       } else if (data[0].equalsIgnoreCase("debug")) {
/* 132 */         vadebug(sender);
/* 133 */       } else if (data[0].equalsIgnoreCase("skip")) {
/* 134 */         vaskip(sender);
/* 135 */       } else if (data[0].equalsIgnoreCase("uti")) {
/* 136 */         vauti(sender);
/* 137 */       } else if (data[0].equalsIgnoreCase("maker")) {
/* 138 */         vamaker(sender);
/* 139 */       } else if (data[0].equalsIgnoreCase("abi")) {
/* 140 */         vaabi(sender, data);
/* 141 */       } else if (data[0].equalsIgnoreCase("ablist")) {
/* 142 */         vaablist(sender, data);
/* 143 */       } else if (data[0].equalsIgnoreCase("inv")) {
/* 144 */         vainv(sender);
/* 145 */       } else if (data[0].equalsIgnoreCase("go")) {
/* 146 */         vago(sender);
/*     */       }
/*     */     } else
/* 149 */       sender.sendMessage("플러그인이 개조되었습니다. 게임을 진행할 수 없습니다.");
/* 150 */     return true;
/*     */   }
/*     */   
/*     */   public final void vaablist(CommandSender p, String[] d) {
/* 154 */     if (p.isOp()) {
/* 155 */       int page = 0;
/* 156 */       if (d.length == 2) {
/*     */         try {
/* 158 */           page = Integer.valueOf(d[1]).intValue();
/* 159 */           if (page >= 0) {
/* 160 */             p.sendMessage(ChatColor.GOLD + "==== 능력 목록 및 코드 ====");
/* 161 */             p.sendMessage(String.format(ChatColor.AQUA + 
/* 162 */               "페이지 %d...[0~10]", 
/* 163 */               new Object[] { Integer.valueOf(page) }));
/* 164 */             for (int code = page * 8; code < (page + 1) * 8; code++) {
/* 165 */               if (code < AbilityList.AbilityList.size()) {
/* 166 */                 AbilityBase a = 
/* 167 */                   (AbilityBase)AbilityList.AbilityList.get(code);
/* 168 */                 p.sendMessage(String.format(
/* 169 */                   ChatColor.GREEN + "[%d] " + 
/* 170 */                   ChatColor.WHITE + "%s", 
/* 171 */                   new Object[] { Integer.valueOf(code), 
/* 172 */                   a.GetAbilityName() }));
/*     */               }
/*     */             }
/* 175 */             p.sendMessage(ChatColor.GOLD + "================");
/*     */           } else {
/* 177 */             p.sendMessage(ChatColor.RED + "페이지가 올바르지 않습니다.");
/*     */           }
/*     */         } catch (NumberFormatException e) {
/* 180 */           p.sendMessage(ChatColor.RED + "페이지가 올바르지 않습니다.");
/* 181 */           return;
/*     */         }
/*     */       } else
/* 184 */         p.sendMessage(ChatColor.RED + 
/* 185 */           "명령이 올바르지 않습니다. [/va ablist [0~10]");
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vaabi(CommandSender p, String[] d) {
/* 190 */     if (p.isOp()) {
/* 191 */       if (d.length == 3) {
/* 192 */         Player pn = Bukkit.getServer().getPlayerExact(d[1]);
/* 193 */         if ((pn != null) || (d[1].equalsIgnoreCase("null"))) {
/* 194 */           int abicode = 0;
/*     */           try {
/* 196 */             abicode = Integer.valueOf(d[2]).intValue();
/*     */           } catch (NumberFormatException e) {
/* 198 */             p.sendMessage(ChatColor.RED + "능력 코드가 올바르지 않습니다.");
/* 199 */             return;
/*     */           }
/* 201 */           if (abicode == -1) {
/* 202 */             for (AbilityBase ab : AbilityList.AbilityList) {
/* 203 */               if (ab.PlayerCheck(pn)) {
/* 204 */                 ab.SetPlayer(null, true);
/*     */               }
/*     */             }
/* 207 */             pn.sendMessage(ChatColor.RED + "당신의 능력이 모두 해제되었습니다.");
/* 208 */             p.sendMessage(String.format(ChatColor.GREEN + "%s" + 
/* 209 */               ChatColor.WHITE + "님의 능력을 모두 해제했습니다.", 
/* 210 */               new Object[] { pn.getName() }));
/* 211 */             Bukkit.broadcastMessage(String.format(ChatColor.GOLD + 
/* 212 */               "%s님이 누군가의 능력을 모두 해제했습니다.", 
/* 213 */               new Object[] { p.getName() }));
/* 214 */             return;
/*     */           }
/* 216 */           if ((abicode >= 0) && 
/* 217 */             (abicode < AbilityList.AbilityList.size())) {
/* 218 */             AbilityBase a = 
/* 219 */               (AbilityBase)AbilityList.AbilityList.get(abicode);
/* 220 */             if (d[1].equalsIgnoreCase("null")) {
/* 221 */               a.SetPlayer(null, true);
/* 222 */               p.sendMessage(String.format("%s 능력 초기화 완료", 
/* 223 */                 new Object[] { a.GetAbilityName() }));
/* 224 */               return;
/*     */             }
/*     */             
/* 227 */             if (PhysicalFighters.AbilityOverLap) {
/* 228 */               if ((a.GetAbilityType() == AbilityBase.Type.Active_Continue) || 
/* 229 */                 (a.GetAbilityType() == AbilityBase.Type.Active_Immediately)) {
/* 230 */                 for (AbilityBase ab : AbilityList.AbilityList) {
/* 231 */                   if ((ab.PlayerCheck(pn)) && (
/* 232 */                     (ab.GetAbilityType() == AbilityBase.Type.Active_Continue) || 
/* 233 */                     (ab.GetAbilityType() == AbilityBase.Type.Active_Immediately))) {
/* 234 */                     ab.SetPlayer(null, true);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             } else {
/* 239 */               for (AbilityBase ab : AbilityList.AbilityList) {
/* 240 */                 if (ab.PlayerCheck(pn)) {
/* 241 */                   ab.SetPlayer(null, true);
/*     */                 }
/*     */               }
/*     */             }
/* 245 */             a.SetPlayer(pn, true);
/* 246 */             a.SetRunAbility(true);
/* 247 */             p.sendMessage(
/* 248 */               String.format(ChatColor.GREEN + "%s" + 
/* 249 */               ChatColor.WHITE + "님에게 " + 
/* 250 */               ChatColor.GREEN + "%s" + 
/* 251 */               ChatColor.WHITE + " 능력 할당이 완료되었습니다.", 
/* 252 */               new Object[] { pn.getName(), 
/* 253 */               a.GetAbilityName() }));
/* 254 */             Bukkit.broadcastMessage(String.format(ChatColor.GOLD + 
/* 255 */               "%s님이 누군가에게 능력을 강제로 할당했습니다.", 
/* 256 */               new Object[] { p.getName() }));
/* 258 */             String s; if ((p instanceof Player)) {
/* 259 */               s = p.getName();
/*     */             } else
/* 261 */               s = "서버 개설자";
/* 262 */             PhysicalFighters.log.info(String.format(
/* 263 */               "%s님이 %s님에게 %s 능력을 할당했습니다.", new Object[] { s, 
/* 264 */               pn.getName(), a.GetAbilityName() }));
/*     */           } else {
/* 266 */             p.sendMessage(ChatColor.RED + "능력 코드가 올바르지 않습니다.");
/*     */           }
/*     */         } else {
/* 269 */           p.sendMessage(ChatColor.RED + "존재하지 않는 플레이어입니다.");
/*     */         }
/*     */       } else {
/* 272 */         p.sendMessage(ChatColor.RED + 
/* 273 */           "명령이 올바르지 않습니다. [/va abi [플레이어] [명령코드]]");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vabook(Player p, String[] d)
/*     */   {
/* 280 */     if (p.isOp())
/* 281 */       if (d.length == 2) {
/* 282 */         int abicode = 0;
/*     */         try {
/* 284 */           abicode = Integer.valueOf(d[1]).intValue();
/*     */         } catch (NumberFormatException e) {
/* 286 */           p.sendMessage(ChatColor.RED + "능력 코드가 올바르지 않습니다.");
/* 287 */           return;
/*     */         }
/* 289 */         if ((abicode >= 0) && 
/* 290 */           (abicode < AbilityList.AbilityList.size())) {
/* 291 */           AbilityBase a = 
/* 292 */             (AbilityBase)AbilityList.AbilityList.get(abicode);
/*     */           
/* 294 */           p.sendMessage("능력서를 만들었습니다. " + ChatColor.GOLD + a.GetAbilityName());
/* 295 */           ItemStack is = new ItemStack(Material.ENCHANTED_BOOK);
/* 296 */           ItemMeta im = is.getItemMeta();
/* 297 */           im.setDisplayName(ChatColor.GOLD + "[능력서]" + ChatColor.WHITE + abicode + "." + ChatColor.AQUA + a.GetAbilityName());
/* 298 */           im.setLore(a.GetGuide2());
/* 299 */           is.setItemMeta(im);
/* 300 */           p.getInventory().addItem(new ItemStack[] { is });
/*     */         }
/*     */         else {
/* 303 */           p.sendMessage(ChatColor.RED + "능력 코드가 올바르지 않습니다.");
/*     */         }
/*     */       } else {
/* 306 */         p.sendMessage(ChatColor.RED + 
/* 307 */           "명령이 올바르지 않습니다. [/va book [능력코드]]");
/*     */       }
/*     */   }
/*     */   
/*     */   public final void vauti(CommandSender p) {
/* 312 */     if (p.isOp()) {
/* 313 */       p.sendMessage(ChatColor.DARK_RED + "Physical Fighters 명령어 목록");
/* 314 */       p.sendMessage(ChatColor.GRAY + "명령어는 /va [명령어]로 사용합니다.");
/* 315 */       p.sendMessage(ChatColor.RED + "alist : " + ChatColor.WHITE + 
/* 316 */         "능력자 목록을 봅니다. 옵 전용.");
/* 317 */       p.sendMessage(ChatColor.RED + "elist : " + ChatColor.WHITE + 
/* 318 */         "능력 확정이 안된 사람들을 보여줍니다. 옵 전용.");
/* 319 */       p.sendMessage(ChatColor.RED + "ablist [페이지(0~2)] : " + 
/* 320 */         ChatColor.WHITE + "능력 목록 및 능력 코드를 보여줍니다. 옵 전용.");
/* 321 */       p.sendMessage(ChatColor.RED + "abi [닉네임] [능력 코드] : " + 
/* 322 */         ChatColor.WHITE + "특정 플레이어에게 능력을 강제로 할당합니다. 같은 능력을 " + 
/* 323 */         "여럿이서 가질수는 없으며 이미 할당된 능력을 타인에게 " + 
/* 324 */         "주면 기존에 갖고있던 사람의 능력은 사라지게 됩니다. " + 
/* 325 */         "액티브 능력은 두 종류 이상 중복해서 줄수 없습니다. " + 
/* 326 */         "게임을 시작하지 않더라도 사용이 가능한 명령입니다. " + 
/* 327 */         "닉네임칸에 null을 쓰면 해당 능력에 등록된 플레이어가 " + 
/* 328 */         "해제되며 명령 코드에 -1을 넣으면 해당 플레이어가 가진" + "모든 능력이 해제됩니다.");
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vago(CommandSender p) {
/* 333 */     if (p.isOp()) {
/* 334 */       Bukkit.broadcastMessage(ChatColor.GREEN + 
/* 335 */         "OP에 의해 초반 무적이 해제되었습니다. 이제 데미지를 입습니다.");
/* 336 */       Physical.Fighters.MainModule.EventManager.DamageGuard = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vainv(CommandSender p) {
/* 341 */     if (p.isOp()) {
/* 342 */       Bukkit.broadcastMessage(ChatColor.GREEN + 
/* 343 */         "OP에 의해 초반 무적이 설정되었습니다. 이제 데미지를 입지않습니다.");
/* 344 */       Physical.Fighters.MainModule.EventManager.DamageGuard = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vahungry(CommandSender p) {
/* 349 */     if (p.isOp())
/* 350 */       if (!PhysicalFighters.NoFoodMode) {
/* 351 */         PhysicalFighters.NoFoodMode = true;
/* 352 */         Bukkit.broadcastMessage(ChatColor.GREEN + 
/* 353 */           "OP에 의해 배고픔무한이 설정되었습니다.");
/*     */       } else {
/* 355 */         PhysicalFighters.NoFoodMode = false;
/* 356 */         Bukkit.broadcastMessage(ChatColor.RED + 
/* 357 */           "OP에 의해 배고픔무한이 해제되었습니다.");
/*     */       }
/*     */   }
/*     */   
/*     */   public final void vadura(CommandSender p) {
/* 362 */     if (p.isOp())
/* 363 */       if (!PhysicalFighters.InfinityDur) {
/* 364 */         PhysicalFighters.InfinityDur = true;
/* 365 */         Bukkit.broadcastMessage(ChatColor.GREEN + 
/* 366 */           "OP에 의해 내구도무한이 설정되었습니다.");
/*     */       } else {
/* 368 */         PhysicalFighters.InfinityDur = false;
/* 369 */         Bukkit.broadcastMessage(ChatColor.RED + 
/* 370 */           "OP에 의해 내구도무한이 해제되었습니다.");
/*     */       }
/*     */   }
/*     */   
/*     */   public final void vadebug(CommandSender p) {
/* 375 */     if (p.isOp()) {
/* 376 */       p.sendMessage(ChatColor.DARK_RED + "Physical Fighters Debug");
/* 377 */       p.sendMessage(ChatColor.RED + "tc : " + ChatColor.WHITE + 
/* 378 */         "[Debug] 모든 능력의 지속 효과 및 쿨타임을 초기화 합니다.");
/* 379 */       p.sendMessage(ChatColor.RED + "kill 닉네임 : " + ChatColor.WHITE + 
/* 380 */         "[Debug] 플러그인 내에서 이 플레이어를 사망 처리합니다.");
/* 381 */       p.sendMessage(ChatColor.RED + "skip : " + ChatColor.WHITE + 
/* 382 */         "[Debug] 모든 능력을 강제로 확정시킵니다.");
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vamaker(CommandSender p) {
/* 387 */     if (p.isOp()) {
/* 388 */       p.sendMessage(ChatColor.DARK_RED + "Physical Fighters 제작자");
/* 389 */       p.sendMessage(ChatColor.RED + 
/* 390 */         " 본 모드는 '제온'님이 배포하신 'VisualAbility'의 모듈을 사용하고있습니다. " + 
/* 391 */         ChatColor.WHITE);
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vaskip(CommandSender p) {
/* 396 */     if (p.isOp())
/* 397 */       if (Scenario == ScriptStatus.AbilitySelect) {
/* 398 */         Bukkit.broadcastMessage(String.format(ChatColor.GRAY + 
/* 399 */           "관리자 %s님이 능력을 강제로 확정시켰습니다.", 
/* 400 */           new Object[] { p.getName() }));
/* 401 */         this.OKSign.clear();
/* 402 */         for (Player pl : PlayerList) {
/* 403 */           this.OKSign.add(pl);
/*     */         }
/* 405 */         this.s_GameStart.GameStart();
/*     */       } else {
/* 407 */         p.sendMessage(ChatColor.RED + "능력 추첨중이 아닙니다.");
/*     */       }
/*     */   }
/*     */   
/*     */   public final void vatc(CommandSender p) {
/* 412 */     if (p.isOp()) {
/* 413 */       for (AbilityBase a : AbilityList.AbilityList) {
/* 414 */         a.AbilityDTimerCancel();
/* 415 */         a.AbilityCTimerCancel();
/*     */       }
/* 417 */       Bukkit.broadcastMessage(String.format(ChatColor.GRAY + 
/* 418 */         "관리자 %s님이 쿨타임및 지속시간을 초기화했습니다.", 
/* 419 */         new Object[] { p.getName() }));
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vakill(CommandSender p, String[] d) {
/* 424 */     if (p.isOp())
/* 425 */       if (d.length == 2) {
/* 426 */         Player pn = Bukkit.getServer().getPlayerExact(d[1]);
/* 427 */         if (pn != null) {
/* 428 */           AbilityBase a = AbilityBase.FindAbility(pn);
/* 429 */           if (a != null) {
/* 430 */             a.AbilityDTimerCancel();
/* 431 */             a.AbilityCTimerCancel();
/*     */           }
/* 433 */           pn.damage((int) 5000.0D);
/* 434 */           pn.kickPlayer("관리자가 당신의 의지를 꺾었습니다.");
/* 435 */           Bukkit.broadcastMessage(String.format(ChatColor.GRAY + 
/* 436 */             "%s님이 %s님을 사망처리했습니다.", new Object[] {
/* 437 */             p.getName(), pn.getName() }));
/*     */         }
/*     */       } else {
/* 440 */         p.sendMessage("명령이 올바르지 않습니다.");
/*     */       }
/*     */   }
/*     */   
/*     */   public final void vaeasteregg(Player p) throws IOException {
/* 445 */     File f = new File("yeomryo.love");
/* 446 */     if (!f.exists()) {
/* 447 */       if (!PhysicalFighters.easteregg) {
/* 448 */         p.sendMessage("이스터에그의 히든능력이 적용되었습니다.");
/* 449 */         PhysicalFighters.easteregg = true;
/* 450 */         f.createNewFile();
/* 451 */         Bukkit.reload();
/*     */       } else {
/* 453 */         p.sendMessage("이미 히든능력이 사용되고 있습니다.(없애려면 폴더의 yeomryo.love파일을 제거해주세요.)");
/*     */       }
/*     */     }
/*     */     else
/* 457 */       p.sendMessage("하이!");
/*     */   }
/*     */   
/*     */   public final void vaelist(CommandSender p) {
/* 461 */     if (p.isOp()) {
/* 462 */       if (Scenario == ScriptStatus.AbilitySelect) {
/* 463 */         p.sendMessage(ChatColor.GOLD + "- 확정하지 않은 사람 -");
/* 464 */         p.sendMessage(ChatColor.GREEN + "---------------");
/* 465 */         List<AbilityBase> pl = AbilityList.AbilityList;
/* 466 */         int count = 0;
/*     */         
/* 468 */         for (int l = 0; l < pl.size(); l++) {
/* 469 */           if (((AbilityBase)pl.get(l)).GetPlayer() != null) {
/* 470 */             AbilityBase tempab = (AbilityBase)pl.get(l);
/* 471 */             if (!this.OKSign.contains(tempab.GetPlayer())) {
/* 472 */               p.sendMessage(String.format(ChatColor.GREEN + 
/* 473 */                 "%d. " + ChatColor.WHITE + "%s", new Object[] { Integer.valueOf(count), 
/* 474 */                 tempab.GetPlayer().getName() }));
/* 475 */               count++;
/*     */             }
/*     */           }
/*     */         }
/* 479 */         p.sendMessage(ChatColor.GREEN + "---------------");
/*     */       } else {
/* 481 */         p.sendMessage(ChatColor.RED + "능력 추첨중에만 가능합니다.");
/*     */       }
/*     */     } else {
/* 484 */       p.sendMessage(ChatColor.RED + "당신은 권한이 없습니다. 관리자에게 OP 권한을 요청하세요.");
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vaalist(CommandSender p) {
/* 489 */     if (p.isOp()) {
/* 490 */       Bukkit.broadcastMessage(String.format(ChatColor.GREEN + 
/* 491 */         "%s님이 플레이어들의 능력을 확인했습니다.", new Object[] { p.getName() }));
/* 492 */       p.sendMessage(ChatColor.GOLD + "- 능력을 스캔했습니다. -");
/* 493 */       p.sendMessage(ChatColor.GREEN + "---------------");
/* 494 */       List<AbilityBase> pl = AbilityList.AbilityList;
/* 495 */       int count = 0;
/*     */       
/* 497 */       for (int l = 0; l < pl.size(); l++) {
/* 498 */         if (((AbilityBase)pl.get(l)).GetPlayer() != null) {
/* 499 */           Player temp = Bukkit.getServer().getPlayer(
/* 500 */             ((AbilityBase)pl.get(l)).GetPlayer().getName());
/* 501 */           AbilityBase tempab = (AbilityBase)pl.get(l);
/* 502 */           if (temp != null) {
/* 503 */             p.sendMessage(String.format(
/* 504 */               ChatColor.GREEN + "%d. " + ChatColor.WHITE + 
/* 505 */               "%s : " + ChatColor.RED + "%s " + 
/* 506 */               ChatColor.WHITE + "[" + 
/* 507 */               AUC.TypeTextOut(tempab) + "]", 
/* 508 */               new Object[] { Integer.valueOf(count), 
/* 509 */               temp.getName(), tempab.GetAbilityName() }));
/* 510 */             count++;
/*     */           }
/*     */         }
/*     */       }
/* 514 */       if (count == 0)
/* 515 */         p.sendMessage("아직 능력자가 없습니다.");
/* 516 */       p.sendMessage(ChatColor.GREEN + "---------------");
/*     */     } else {
/* 518 */       p.sendMessage(ChatColor.RED + "당신은 권한이 없습니다. 관리자에게 OP 권한을 요청하세요.");
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vastop(CommandSender p) {
/* 523 */     if (p.isOp()) {
/* 524 */       if (Scenario != ScriptStatus.NoPlay) {
/* 525 */         S_GameStart.PlayDistanceBuffer = 0;
/* 526 */         Bukkit.broadcastMessage(ChatColor.GRAY + 
/* 527 */           "------------------------------");
/* 528 */         if (Scenario != ScriptStatus.GameStart) {
/* 529 */           Bukkit.broadcastMessage(String.format(ChatColor.YELLOW + 
/* 530 */             "%s님이 게임 카운터를 중단시켰습니다.", 
/* 531 */             new Object[] { p.getName() }));
/*     */         } else
/* 533 */           Bukkit.broadcastMessage(String.format(ChatColor.YELLOW + 
/* 534 */             "%s님이 게임 카운터를 중단시켰습니다.", 
/* 535 */             new Object[] { p.getName() }));
/* 536 */         Scenario = ScriptStatus.NoPlay;
/* 537 */         this.s_GameReady.GameReadyStop();
/* 538 */         this.s_GameStart.GameStartStop();
/* 539 */         this.s_GameProgress.GameProgressStop();
/* 540 */         this.s_GameWarnning.GameWarnningStop();
/* 541 */         Bukkit.broadcastMessage(ChatColor.GRAY + "모든 설정이 취소됩니다.");
/* 542 */         Bukkit.broadcastMessage(ChatColor.GREEN + 
/* 543 */           "옵저버 설정은 초기화 되지 않습니다.");
/* 544 */         this.OKSign.clear();
/* 545 */         Physical.Fighters.MainModule.EventManager.DamageGuard = false;
/* 546 */         for (int l = 0; l < AbilityList.AbilityList.size(); l++)
/*     */         {
/* 548 */           ((AbilityBase)AbilityList.AbilityList.get(l)).AbilityDTimerCancel();
/* 549 */           ((AbilityBase)AbilityList.AbilityList.get(l))
/* 550 */             .AbilityCTimerCancel();
/* 551 */           ((AbilityBase)AbilityList.AbilityList.get(l))
/* 552 */             .SetRunAbility(false);
/* 553 */           ((AbilityBase)AbilityList.AbilityList.get(l)).SetPlayer(
/* 554 */             null, false);
/*     */         }
/* 556 */         PlayerList.clear();
/*     */       } else {
/* 558 */         p.sendMessage(ChatColor.RED + "아직 게임을 시작하지 않았습니다.");
/*     */       }
/*     */     } else
/* 561 */       p.sendMessage(ChatColor.RED + "당신은 권한이 없습니다. 관리자에게 OP 권한을 요청하세요.");
/*     */   }
/*     */   
/*     */   public final void vaob(Player p) {
/* 565 */     if (Scenario == ScriptStatus.NoPlay) {
/* 566 */       if (this.ExceptionList.contains(p)) {
/* 567 */         PlayerList.add(p);
/* 568 */         this.ExceptionList.remove(p);
/* 569 */         p.sendMessage(ChatColor.GREEN + "게임 예외처리가 해제되었습니다.");
/*     */       } else {
/* 571 */         this.ExceptionList.add(p);
/* 572 */         PlayerList.remove(p);
/* 573 */         p.sendMessage(ChatColor.GOLD + "게임 예외처리가 완료되었습니다.");
/* 574 */         p.sendMessage(ChatColor.GREEN + "/va ob을 다시 사용하시면 해제됩니다.");
/*     */       }
/*     */     } else
/* 577 */       p.sendMessage(ChatColor.RED + "게임 시작 이후는 옵저버 처리가 불가능합니다.");
/*     */   }
/*     */   
/*     */   public final void vayes(Player p) {
/* 581 */     if ((Scenario == ScriptStatus.AbilitySelect) && 
/* 582 */       (!this.ExceptionList.contains(p)) && 
/* 583 */       (!this.OKSign.contains(p))) {
/* 584 */       this.OKSign.add(p);
/* 585 */       p.sendMessage(ChatColor.GOLD + "능력이 확정되었습니다. 다른 사람을 기다리세요.");
/* 586 */       Bukkit.broadcastMessage(String.format(ChatColor.YELLOW + "%s" + 
/* 587 */         ChatColor.WHITE + "님이 능력을 확정했습니다.", 
/* 588 */         new Object[] { p.getName() }));
/* 589 */       Bukkit.broadcastMessage(String.format(
/* 590 */         ChatColor.GREEN + "남은 인원 : " + ChatColor.WHITE + "%d명", 
/* 591 */         new Object[] {
/* 592 */         Integer.valueOf(PlayerList.size() - this.OKSign.size()) }));
/* 593 */       if (this.OKSign.size() == PlayerList.size())
/* 594 */         this.s_GameStart.GameStart();
/*     */     }
/*     */   }
/*     */   
/*     */   public final void vano(Player p) {
/* 599 */     if ((Scenario == ScriptStatus.AbilitySelect) && 
/* 600 */       (!this.ExceptionList.contains(p)) && 
/* 601 */       (!this.OKSign.contains(p))) {
/* 602 */       if (reRandomAbility(p) == null) {
/* 603 */         p.sendMessage(ChatColor.RED + "경고, 능력의 갯수가 부족합니다.");
/* 604 */         return;
/*     */       }
/* 606 */       AUC.InfoTextOut(p);
/* 607 */       this.OKSign.add(p);
/* 608 */       p.sendMessage(ChatColor.GOLD + "능력이 자동으로 확정되었습니다. 다른 사람을 기다리세요.");
/* 609 */       Bukkit.broadcastMessage(String.format(ChatColor.YELLOW + "%s" + 
/* 610 */         ChatColor.WHITE + "님이 능력을 확정했습니다.", 
/* 611 */         new Object[] { p.getName() }));
/* 612 */       Bukkit.broadcastMessage(String.format(
/* 613 */         ChatColor.GREEN + "남은 인원 : " + ChatColor.WHITE + "%d명", 
/* 614 */         new Object[] {
/* 615 */         Integer.valueOf(PlayerList.size() - this.OKSign.size()) }));
/* 616 */       if (this.OKSign.size() == PlayerList.size())
/* 617 */         this.s_GameStart.GameStart();
/*     */     }
/*     */   }
/*     */   
/*     */   private AbilityBase reRandomAbility(Player p) {
/* 622 */     ArrayList<AbilityBase> Alist = new ArrayList();
/* 623 */     Random r = new Random();
/* 624 */     int Findex = r.nextInt(AbilityList.AbilityList.size() - 1);
/* 626 */     int saveindex; if (Findex == 0) {
/* 627 */       saveindex = AbilityList.AbilityList.size();
/*     */     } else {
/* 629 */       saveindex = Findex - 1;
/*     */     }
/* 631 */     for (int i = 0; i < AbilityList.AbilityList.size(); i++) {
/* 632 */       if (((AbilityBase)AbilityList.AbilityList.get(Findex)).PlayerCheck(p)) {
/* 633 */         ((AbilityBase)AbilityList.AbilityList.get(Findex)).SetPlayer(null, false);
/* 634 */       } else if ((((AbilityBase)AbilityList.AbilityList.get(Findex)).GetPlayer() == null) && (
/* 635 */         (PlayerList.size() > 6) || 
/* 636 */         (AbilityList.AbilityList.get(Findex) != AbilityList.mirroring)))
/*     */       {
/* 638 */         Alist.add((AbilityBase)AbilityList.AbilityList.get(Findex));
/*     */       }
/*     */       
/* 641 */       Findex++;
/*     */       
/* 643 */       if (Findex == saveindex)
/*     */         break;
/* 645 */       if (Findex == AbilityList.AbilityList.size())
/* 646 */         Findex = 0;
/*     */     }
/* 648 */     if (Alist.size() == 0) {
/* 649 */       return null;
/*     */     }
/* 651 */     if (Alist.size() == 1) {
/* 652 */       ((AbilityBase)Alist.get(0)).SetPlayer(p, false);
/* 653 */       return (AbilityBase)Alist.get(0);
/*     */     }
/*     */     
/* 656 */     int ran2 = r.nextInt(Alist.size() - 1);
/* 657 */     ((AbilityBase)Alist.get(ran2)).SetPlayer(p, false);
/* 658 */     return (AbilityBase)Alist.get(ran2);
/*     */   }
/*     */   
/*     */   public static enum ScriptStatus {
/* 662 */     NoPlay,  ScriptStart,  AbilitySelect,  GameStart;
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\Script\MainScripter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */