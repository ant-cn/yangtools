/*
 * Copyright (c) 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.model.api.stmt;

import java.util.Collection;
import javax.annotation.Nonnull;

public interface UsesStatement extends DataDefinitionStatement {
    default @Nonnull Collection<? extends RefineStatement> getRefines() {
        return declaredSubstatements(RefineStatement.class);
    }

    default @Nonnull Collection<? extends AugmentStatement> getAugments() {
        return declaredSubstatements(AugmentStatement.class);
    }
}
