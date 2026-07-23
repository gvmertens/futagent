CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE user_preference (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    category VARCHAR(32) NOT NULL,
    canonical_id VARCHAR(160) NOT NULL,
    label VARCHAR(160) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_user_preference UNIQUE (user_id, category, canonical_id)
);

CREATE INDEX idx_user_preference_user ON user_preference(user_id);

CREATE TABLE knowledge_chunk (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    competition_id VARCHAR(160),
    source_url TEXT NOT NULL,
    source_title TEXT NOT NULL,
    source_updated_at TIMESTAMPTZ,
    content TEXT NOT NULL,
    embedding vector(1536),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_knowledge_embedding ON knowledge_chunk
    USING hnsw (embedding vector_cosine_ops);

CREATE TABLE calendar_sync (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    fixture_id VARCHAR(160) NOT NULL,
    provider VARCHAR(32) NOT NULL,
    remote_event_id VARCHAR(255),
    content_hash VARCHAR(64) NOT NULL,
    sync_status VARCHAR(32) NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_calendar_sync UNIQUE (user_id, provider, fixture_id)
);

