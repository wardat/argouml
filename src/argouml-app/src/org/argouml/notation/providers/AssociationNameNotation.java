/* $Id: AssociationNameNotation.java 18852 2010-11-20 19:27:11Z mvw $
 *****************************************************************************
 * Copyright (c) 2009-2010 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Michiel van der Wulp
 *****************************************************************************
 *
 * Some portions of this file was previously release using the BSD License:
 */

// Copyright (c) 2006-2009 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies. This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason. IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.notation.providers;

import java.util.Collection;
import java.util.Iterator;

import org.argouml.model.Model;
import org.argouml.notation.NotationProvider;

/**
 * This abstract class forms the basis of all Notation providers
 * for the text shown above the association Fig.
 * Subclass this for all languages.
 *
 * @author Michiel van der Wulp
 */
public abstract class AssociationNameNotation extends NotationProvider {

    /**
     * The constructor.
     * 
     * @param modelElement the UML Association
     */
    public AssociationNameNotation(Object modelElement) {
        if (!Model.getFacade().isAAssociation(modelElement)) {
            throw new IllegalArgumentException("This is not an Association.");
        }
    }

    @Override
    public void initialiseListener(Object modelElement) {
        /* Listen to the modelelement itself: */
        addElementListener(modelElement, 
                new String[] {"name", "visibility", "stereotype"});
        Collection stereotypes =
            Model.getFacade().getStereotypes(modelElement);
        Iterator iter = stereotypes.iterator();
        while (iter.hasNext()) {
            Object oneStereoType = iter.next();
            addElementListener(
                    oneStereoType, 
                    new String[] {"name", "remove"});
        }
    }
}
