/*
 * Copyright (c) 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.model.api.stmt;

import java.util.Optional;
import javax.annotation.Nullable;
import org.opendaylight.yangtools.yang.common.QName;

public interface ContainerStatement extends DataDefinitionStatement,
        DataDefinitionAwareDeclaredStatement.WithReusableDefinitions<QName>,
        ActionStatementAwareDeclaredStatement<QName>, ConfigStatementAwareDeclaredStatement<QName>,
        NotificationStatementAwareDeclaredStatement<QName>, MustStatementAwareDeclaredStatement<QName> {
    default @Nullable PresenceStatement getPresence() {
        final Optional<PresenceStatement> opt = findFirstDeclaredSubstatement(PresenceStatement.class);
        return opt.isPresent() ? opt.get() : null;
    }
}
