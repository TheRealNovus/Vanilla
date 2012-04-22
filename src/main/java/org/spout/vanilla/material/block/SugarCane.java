/*
 * This file is part of Vanilla (http://www.spout.org/).
 *
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.material.block;

import java.util.HashSet;
import java.util.Set;

import org.spout.api.Source;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;
import org.spout.api.inventory.ItemStack;
import org.spout.api.material.Material;
import org.spout.api.material.block.BlockFace;

import org.spout.vanilla.controller.object.moving.Item;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.material.block.generic.VanillaBlockMaterial;

public class SugarCane extends VanillaBlockMaterial {
	private final Set<Material> validBases = new HashSet<Material>(4);

	public SugarCane() {
		super("Sugar Cane", 83);

		validBases.add(VanillaMaterials.DIRT);
		validBases.add(VanillaMaterials.GRASS);
		validBases.add(VanillaMaterials.SAND);
		validBases.add(VanillaMaterials.SUGAR_CANE_BLOCK);
	}

	@Override
	public boolean hasPhysics() {
		return true;
	}

	@Override
	public boolean canPlace(Block block, short data, BlockFace against, Source source) {
		if (super.canPlace(block, data, against, source)) {
			if (validBases.contains(block.getMaterial())) {
				block = block.translate(against.getOpposite());
				return block.getMaterial() == VanillaMaterials.WATER;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void onUpdate(Block block) {
		int amount = 0;
		Block tmpblock = block;
		while ((tmpblock = tmpblock.translate(BlockFace.TOP)).getMaterial().equals(VanillaMaterials.SUGAR_CANE_BLOCK)) {
			amount++;
		}

		if (!validBases.contains(block.translate(BlockFace.BOTTOM).getMaterial())) {
			Point point = block.getPosition();
			block.setMaterial(VanillaMaterials.AIR).update(true);
			block.getWorld().createAndSpawnEntity(point, new Item(new ItemStack(VanillaMaterials.SUGAR_CANE, amount), point.normalize()));
		}
	}
}
