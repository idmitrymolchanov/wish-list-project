--liquibase formatted sql
--changeset dpmolchanov:1
CREATE TABLE task
(
    id                          UUID PRIMARY KEY,
    create_date                 TIMESTAMP NOT NULL,
    last_update_date            TIMESTAMP,
    code                        VARCHAR(255) NOT NULL,
    name                        VARCHAR(255),
    status_code                 VARCHAR(50) NOT NULL,
    status_name                 VARCHAR(255),
    source_create_date          TIMESTAMP,
    source_process_id           VARCHAR(255),
    source_process_instance_id VARCHAR(255),
    source_task_id              VARCHAR(255) NOT NULL,
    source_task_instance_key    VARCHAR(255),
    business_key                UUID NOT NULL,
    application_number          VARCHAR(255),
    assignee                    UUID,
    assignee_name               VARCHAR(255),
    repeat                      BOOLEAN,
    repeat_reason               VARCHAR(255),
    plan_start_date             TIMESTAMP,
    plan_complete_date          TIMESTAMP,
    fact_complete_date          TIMESTAMP,
    cancel_reason               VARCHAR(255),
    cancel_reason_name          VARCHAR(255),
    event_type                  VARCHAR(255) NOT NULL,
    changed_by                  UUID,
    changed_by_name             VARCHAR(255),
    variables                   JSONB
);

-- changeset dpmolchanov:1-task-candidate-groups
CREATE TABLE candidate_groups
(
    task_id          UUID,
    candidate_groups VARCHAR(255),
    CONSTRAINT fk_task_candidate_groups FOREIGN KEY (task_id) REFERENCES task (id) ON DELETE CASCADE
);

-- changeset dpmolchanov:1-task-candidate-users
CREATE TABLE candidate_users
(
    task_id         UUID,
    candidate_users UUID,
    CONSTRAINT fk_task_candidate_users FOREIGN KEY (task_id) REFERENCES task (id) ON DELETE CASCADE
);
