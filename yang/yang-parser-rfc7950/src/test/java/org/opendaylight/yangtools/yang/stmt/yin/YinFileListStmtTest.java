/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.stmt.yin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.model.api.DataSchemaNode;
import org.opendaylight.yangtools.yang.model.api.LeafSchemaNode;
import org.opendaylight.yangtools.yang.model.api.ListSchemaNode;
import org.opendaylight.yangtools.yang.model.api.Module;
import org.opendaylight.yangtools.yang.model.api.TypeDefinition;
import org.opendaylight.yangtools.yang.model.api.type.IdentityrefTypeDefinition;
import org.opendaylight.yangtools.yang.model.util.type.BaseTypes;
import org.opendaylight.yangtools.yang.stmt.TestUtils;

public class YinFileListStmtTest extends AbstractYinModulesTest {

    @Test
    public void testListAndLeaves() {
        final Module testModule = TestUtils.findModule(context, "config").get();
        assertNotNull(testModule);

        final ListSchemaNode list = (ListSchemaNode) testModule.findDataChildByName(QName.create(
                testModule.getQNameModule(), "modules"), QName.create(testModule.getQNameModule(), "module")).get();
        final List<QName> keys = list.getKeyDefinition();
        assertEquals(1, keys.size());
        assertEquals("name", keys.get(0).getLocalName());

        final Collection<DataSchemaNode> children = list.getChildNodes();
        assertEquals(4, children.size());

        final Iterator<DataSchemaNode> childrenIterator = children.iterator();
        LeafSchemaNode leaf = (LeafSchemaNode) childrenIterator.next();
        assertEquals("name", leaf.getQName().getLocalName());
        assertEquals(Optional.of("Unique module instance name"), leaf.getDescription());
        assertEquals(BaseTypes.stringType(), leaf.getType());
        assertTrue(leaf.isMandatory());

        leaf = (LeafSchemaNode) childrenIterator.next();
        assertEquals("type", leaf.getQName().getLocalName());

        final TypeDefinition<?> leafType = leaf.getType();
        assertTrue(leafType instanceof IdentityrefTypeDefinition);
        assertEquals("module-type", ((IdentityrefTypeDefinition)leafType).getIdentities().iterator().next().getQName()
            .getLocalName());
        assertTrue(leaf.isMandatory());
    }

}
