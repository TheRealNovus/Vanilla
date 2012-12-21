/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * Vanilla is licensed under the Spout License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.vanilla.event.player;

import org.spout.api.entity.Player;
import org.spout.api.event.HandlerList;
import org.spout.api.event.player.PlayerEvent;
import org.spout.api.protocol.event.ProtocolEvent;
import org.spout.vanilla.component.player.PlayerAbilityComponent;

public class PlayerAbilityUpdateEvent extends PlayerEvent implements ProtocolEvent {
	
	private static HandlerList handlers = new HandlerList();

	private final byte flyingSpeed;
	private final byte walkingSpeed;
	private final boolean godMode;
	private final boolean isFlying;
	private final boolean canFly;
	private final boolean creativeMode;
	
	public PlayerAbilityUpdateEvent(Player player) {
		super(player);
		if (!player.has(PlayerAbilityComponent.class)) {
			throw new IllegalStateException("Cannot call PlayerAbilityChangeEvent for players which don't have the PlayerAbilityComponent");
		}
		PlayerAbilityComponent pac = player.get(PlayerAbilityComponent.class);
		flyingSpeed = pac.getFlyingSpeed();
		walkingSpeed = pac.getWalkingSpeed();
		godMode = pac.getGodMode();
		isFlying = pac.isFlying();
		canFly = pac.canFly();
		creativeMode = pac.isCreativeMode();
		
	}
	
	public byte getFlyingSpeed() {
		return flyingSpeed;
	}

	public byte getWalkingSpeed() {
		return walkingSpeed;
	}

	public boolean isFlying() {
		return isFlying;
	}

	public boolean canFly() {
		return canFly;
	}

	public boolean getGodMode() {
		return godMode;
	}

	public boolean isCreativeMode() {
		return creativeMode;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}