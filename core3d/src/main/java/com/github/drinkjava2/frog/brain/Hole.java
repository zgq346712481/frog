/*
 * Copyright 2018 the original author or authors. 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.github.drinkjava2.frog.brain;

import java.io.Serializable;

/**
 * Hole can be input, output, side synapse
 * 
 * 以前叫突触，现在改名叫洞，更形象一点，每个光子传来就好象在果冻上砸出个洞。管它符不符合生物脑突触这个形象，张牙舞爪的神经元变成了千创百孔的果冻，先乱试一通再说。
 * 
 * @author Yong Zhu
 * @since 2.0.2
 */
public class Hole implements Serializable {
	private static final long serialVersionUID = 1L;
	public float x;// x,y,z 是 洞的中心坐标点，这个是脑内的绝对坐标
	public float y;
	public float z;
	public float mx; // mx,my,mz分别是光子砸出这个洞时的光子每单元移动方向在三个轴上的投影
	public float my;
	public float mz;
	public float size;// 洞的大小，同一个方向砸来的光子越多，能量越大，洞就越大
	public int age;// 洞的年龄,一直在增长，但当光子从同一个地方发过来，洞的年龄就归0

	public Hole(Photon p) {
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
		this.mx = p.mx;
		this.my = p.my;
		this.mz = p.mz;
		this.size = p.energy;
	}

	public float angleCompare(Photon p) {// 比较洞与光子之间的角度差值
		return Math.abs(p.mx - mx) + Math.abs(p.my - my) + Math.abs(p.mz - mz);
	}

	public boolean ifParallel(Photon p) {// 如果光子运动方向与洞平行
		return (p.mx - mx < 0.0001 && p.my - my < 0.0001 && p.mz - mz < 0.0001)
				|| (p.mx + mx < 0.0001 && p.my + my < 0.0001 && p.mz + mz < 0.0001);
	}

	public boolean ifSameWay(Photon p) {// 如果光子运动方向与洞同向
		return p.mx - mx < 0.0001 && p.my - my < 0.0001 && p.mz - mz < 0.0001;
	}

	public boolean ifReverseWay(Photon p) {// 如果光子运动方向与洞反向
		return p.mx + mx < 0.0001 && p.my + my < 0.0001 && p.mz + mz < 0.0001;
	}
}
