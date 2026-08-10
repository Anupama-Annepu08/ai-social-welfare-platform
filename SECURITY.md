# Security Policy

## Secrets

No API keys, passwords, JWT secrets, database credentials,
private keys, access tokens, or production credentials should
be committed to this repository.

## Environment Variables

Sensitive configuration must be supplied through environment
variables or an appropriate secret-management mechanism.

## Local Development

Developers should use a local `.env` file or environment
variables for development secrets.

The `.env` file must never be committed to Git.

## Sensitive Data

Real citizen, beneficiary, volunteer, donor, or NGO personal
data must not be committed to the repository.

Development and research should use synthetic or appropriately
licensed public datasets.

## Security Issues

Security vulnerabilities should be reported privately to the
project maintainers rather than disclosed through public issues.

## Secret Exposure

If a secret is accidentally committed:

1. Revoke or rotate the exposed credential immediately.
2. Remove the secret from the working tree.
3. Remove the secret from Git history where necessary.
4. Replace it with a secure environment-based configuration.