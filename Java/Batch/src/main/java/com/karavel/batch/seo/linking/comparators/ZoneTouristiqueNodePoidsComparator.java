package com.karavel.batch.seo.linking.comparators;


import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;

import java.util.Comparator;

/**
 * Comparateur des noeuds en se basant sur les Id des noeuds
 * @author Walid MELLOULI
 *
 */
public class ZoneTouristiqueNodePoidsComparator implements Comparator<ZoneTouristiqueNode> {

	@Override
	public int compare(ZoneTouristiqueNode o1, ZoneTouristiqueNode o2) {
		return o1.getPoids().compareTo(o2.getPoids());
	}

}
