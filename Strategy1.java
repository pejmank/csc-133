package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;
import java.lang.Math;

public class Strategy1 implements Istrategy {

	public int theStrategy1(PlayerCyborg plcyborg, NonPlayerCyborg NPC ) {
		
		Point pc = plcyborg.locationGetter();
		Point npc = NPC.locationGetter();
		int a = (int)pc.getX() - (int)npc.getX();
		int b = (int)pc.getY() - (int)npc.getY();
		return (int) Math.toDegrees(MathUtil.atan2((double)b, (double)a));

	}

	public int theStrategy2(Base base, NonPlayerCyborg NPC) {
		
		Point pc = base.locationGetter();
		Point npc = NPC.locationGetter();
		int a = (int)pc.getX() - (int)npc.getX();
		int b = (int)pc.getY() - (int)npc.getY();
		return (int) Math.toDegrees(MathUtil.atan2((double)b, (double)a));	}

	

	
}
