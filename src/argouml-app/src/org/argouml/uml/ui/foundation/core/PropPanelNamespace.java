/* $Id: PropPanelNamespace.java 18589 2010-07-29 00:26:09Z bobtarling $
 *****************************************************************************
 * Copyright (c) 2009 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    tfmorris
 *****************************************************************************
 *
 * Some portions of this file was previously release using the BSD License:
 */

// Copyright (c) 1996-2007 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
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

package org.argouml.uml.ui.foundation.core;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import org.argouml.model.Model;
import org.argouml.ui.targetmanager.TargetManager;
import org.argouml.uml.ui.ScrollList;

/**
 * The abstract properties panel for a Namespace.
 * @deprecated in 0.31.2 by Bob Tarling  This is replaced by the XML property
 * panels module
 */
@Deprecated
public abstract class PropPanelNamespace extends PropPanelModelElement {

    private JScrollPane ownedElementsScroll;

    private static UMLNamespaceOwnedElementListModel ownedElementListModel =
        new UMLNamespaceOwnedElementListModel();

    /**
     * Construct a property panel for a Namespace with the given name and icon.
     *
     * @param panelName the name of the panel to be shown at the top
     * @param icon the icon to be shown next to the name
     */
    public PropPanelNamespace(String panelName, ImageIcon icon) {
        super(panelName, icon);
    }


    /**
     * Create a class.
     */
    public void addClass() {
        Object target = getTarget();
        if (Model.getFacade().isANamespace(target)) {
            Object ns = target;
            Object ownedElem = Model.getCoreFactory().buildClass();
            Model.getCoreHelper().addOwnedElement(ns, ownedElem);
            TargetManager.getInstance().setTarget(ownedElem);
        }
    }

    /**
     * Create a new interface.
     */
    public void addInterface() {
        Object target = getTarget();
        if (Model.getFacade().isANamespace(target)) {
            Object ns = target;
            Object ownedElem = Model.getCoreFactory().createInterface();
            Model.getCoreHelper().addOwnedElement(ns, ownedElem);
            TargetManager.getInstance().setTarget(ownedElem);
        }
    }

    /**
     * Create a new package within the namespace.
     */
    public void addPackage() {
        Object target = getTarget();
        if (Model.getFacade().isANamespace(target)) {
            Object ns = target;
            Object ownedElem = Model.getModelManagementFactory()
                .createPackage();
            Model.getCoreHelper().addOwnedElement(ns, ownedElem);
            TargetManager.getInstance().setTarget(ownedElem);
        }
    }



    /**
     * Returns the ownedElementsScroll.
     * @return JScrollPane
     */
    public JScrollPane getOwnedElementsScroll() {
        if (ownedElementsScroll == null) {
            ownedElementsScroll =
                    new ScrollList(ownedElementListModel, true, false);
        }
        return ownedElementsScroll;

    }

}
