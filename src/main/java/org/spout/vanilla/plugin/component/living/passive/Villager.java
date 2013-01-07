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
package org.spout.vanilla.plugin.component.living.passive;

import java.util.HashMap;

import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.event.player.PlayerInteractEvent.Action;

import org.spout.vanilla.plugin.VanillaPlugin;
import org.spout.vanilla.plugin.component.living.Living;
import org.spout.vanilla.plugin.inventory.Container;
import org.spout.vanilla.plugin.inventory.CraftingInventory;
import org.spout.vanilla.plugin.inventory.window.Window;
import org.spout.vanilla.plugin.protocol.entity.creature.VillagerEntityProtocol;

import org.spout.vanilla.api.component.Passive;

/**
 * A component that identifies the entity as a Villager.
 */
public class Villager extends Living implements Container, Passive {
	@Override
	public void onAttached() {
		super.onAttached();
		getOwner().getNetwork().setEntityProtocol(VanillaPlugin.VANILLA_PROTOCOL_ID, new VillagerEntityProtocol());
	}

	private HashMap<Player, Window> viewers = new HashMap<Player, Window>();

	@Override
	public void onInteract(Action action, Entity entity) {
		super.onInteract(action, entity);
		if (action == Action.RIGHT_CLICK) {
			// TODO: Open window
		}
	}

	@Override
	public CraftingInventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * The integer value associated with this villager type.
	 * @return int
	 */
	public int getVillagerTypeID() {
		return 5; // Generic Villager ID.
	}
}