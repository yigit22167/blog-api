create table posts(
    id bigserial,
    
    title varchar(150) not null,
    slug varchar(180) not null,
    summary varchar(300) not null,
    content text not null,
    cover_image_url varchar(500),

    status varchar(20) not null default 'DRAFT',

    created_at timestamptz not null default current_timestamp,
    updated_at timestamptz not null default current_timestamp,
    published_at timestamptz,

    constraint pk_posts primary key(id),
    constraint uk_posts_slug unique(slug),
    constraint chk_posts_status check(status in ('DRAFT', 'PUBLISHED')),
    constraint chk_posts_published_at check ((status = 'DRAFT' and published_at is null) or (status = 'PUBLISHED' and published_at is not null) )
);
