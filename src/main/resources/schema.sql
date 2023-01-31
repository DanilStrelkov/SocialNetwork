DROP TYPE IF EXISTS user_status CASCADE;
CREATE TYPE user_status AS ENUM ('ACTIVE', 'INACTIVE');

DROP TYPE IF EXISTS message_permissions CASCADE;
CREATE TYPE message_permissions AS ENUM ('ALL', 'FRIENDS');

DROP TYPE IF EXISTS action_type CASCADE;
CREATE TYPE action_type AS ENUM ('BLOCK', 'UNBLOCK');

DROP TYPE IF EXISTS user_type CASCADE;
CREATE TYPE user_type AS ENUM ('MODERATOR', 'ADMIN');

DROP TYPE IF EXISTS friendship_code CASCADE;
CREATE TYPE friendship_code AS ENUM ('REQUEST', 'FRIEND', 'BLOCKED', 'DECLINED', 'SUBSCRIBED');

DROP TYPE IF EXISTS read_status CASCADE;
CREATE TYPE read_status AS ENUM ('SENT', 'READ');

DROP TYPE IF EXISTS support_request_status CASCADE;
CREATE TYPE support_request_status AS ENUM ('NEW', 'IN_WORK', 'CLOSED');

DROP TYPE IF EXISTS like_type CASCADE;
CREATE TYPE like_type AS ENUM ('POST', 'COMMIT');

DROP TYPE IF EXISTS notification_type CASCADE;
CREATE TYPE notification_type AS ENUM (
    'POST', 'POST_COMMENT', 'COMMENT_COMMENT', 'FRIEND_REQUEST',
    'MESSAGE', 'FRIEND_BIRTHDAY');
